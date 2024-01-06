package oleg.trushanin.graz;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.AreaBreakType;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class ActivityCreateAndSendPDF extends AppCompatActivity {

    PageBreakHandler pageBreakHandler = new PageBreakHandler();
    Button buttonCreateAndSendPDF;
    FloatingActionButton buttonHome;
    Button buttonSend;
    InitViewPosition initViewPosition = InitViewPosition.getInstance();

    Uri uri;

    PdfFont fontBold;
    PdfFont font;
    PdfFont fontItalic;

    SharedPreferences sh;
    String name;
    String nameName;
    String surname;
    String title;
    String phoneNumber;
    String email;

    float totalPriceKp;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;

    TableDataBaseKpViewModel tableDataBaseKpViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_and_send_pdf);
        buttonCreateAndSendPDF = findViewById(R.id.buttonCreateAndSendPDF);
        buttonHome = findViewById(R.id.buttonHome);
        buttonSend = findViewById(R.id.buttonSend);
        tableDataBaseKpViewModel = new ViewModelProvider(this).get(TableDataBaseKpViewModel.class);

        sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        name = sh.getString("имя"," ");
        nameName = sh.getString("отчество","");
        surname = sh.getString("фамилия"," ");
        title = sh.getString("должность","");
        phoneNumber = sh.getString("телефон","");
        email = sh.getString("почта","");
        String numberPrice = sh.getString("нумпрайс"," ");

        createAndSavePDF();

        //Создаем объект для БД
        TableDataBaseKp tableDataBaseKp = new TableDataBaseKp(
                initViewPosition.getModelPPC(),
                initViewPosition.getTypeMaterial(),
                initViewPosition.getVolumePPC(),
                initViewPosition.getCountCapsule(),
                initViewPosition.getTypeAxelCount(),
                initViewPosition.getTypeMarkAxel(),
                initViewPosition.getTypeAxel(),
                initViewPosition.getDateKp(),
                initViewPosition.getClient().isEmpty() ? "Клиент": initViewPosition.getClient(),
                initViewPosition.getTotalPrice(),
                initViewPosition.getPlusPricePpc(),
                uri.toString(),
                numberPrice,
                initViewPosition.getDeliveryCost(),
                totalPriceKp);

        //Записываем объект в БД
        tableDataBaseKpViewModel.insertKpBd(tableDataBaseKp);



        buttonCreateAndSendPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPDF();
            }
        });

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityCreateAndSendPDF.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharePdf();
            }
        });



    }

    private void viewPDF() {

        startActivity(new Intent(this, ActivityViewPdfKp.class));

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            createAndSavePDF();
        } else {
            Toast.makeText(this, "Разрешение на запись не предоставлено", Toast.LENGTH_SHORT).show();
        }
    }

    private void createAndSavePDF() {

        try {
            byte[] pdfContent = createPdfContent();
            savePDFToDownloads("Исх. №" + initViewPosition.getNumberKP() + " " +
                    initViewPosition.getDateKp() + " "
                    +initViewPosition.getType()+" "
                    + initViewPosition.getVolumePPC(), pdfContent);
        } catch (IOException e) {
            e.printStackTrace();

            Toast.makeText(this, "Ошибка при создании PDF: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private byte[] createPdfContent() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);
        pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, pageBreakHandler);
        Document document = new Document(pdfDocument);

        //используем для определения необходимости разрыва страницы
        boolean flagSO = true;
        int totalPages = pageBreakHandler.getPageCount();


        //подключаем шрифт обычный
       font = null;
        try {
            AssetManager assetManager = getAssets(); // Получаем AssetManager
            InputStream inputStream = assetManager.open("timesnewromanpsmt.ttf");
            byte[] fontBytes = IOUtils.toByteArray(inputStream); // Используем Apache Commons IO
            inputStream.close(); // Закрываем потое
            font = PdfFontFactory.createFont(fontBytes, PdfEncodings.IDENTITY_H,
                    PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);
        } catch (IOException e) {
            e.printStackTrace();
            // Обработка ошибки
        }
        //подключаем шрифт жирный
        fontBold = null;
        try {
            AssetManager assetManager = getAssets(); // Получаем AssetManager
            InputStream inputStream = assetManager.open("TimesNewRomanBold.ttf");
            byte[] fontBytes = IOUtils.toByteArray(inputStream); // Используем Apache Commons IO
            inputStream.close(); // Закрываем потое
            fontBold = PdfFontFactory.createFont(fontBytes, PdfEncodings.IDENTITY_H,
                    PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);
        } catch (IOException e) {
            e.printStackTrace();
            // Обработка ошибки
        }
        fontItalic = null;
        try {
            AssetManager assetManager = getAssets(); // Получаем AssetManager
            InputStream inputStream = assetManager.open("TimesNewRomanItalic.ttf");
            byte[] fontBytes = IOUtils.toByteArray(inputStream); // Используем Apache Commons IO
            inputStream.close(); // Закрываем потое
            fontItalic = PdfFontFactory.createFont(fontBytes, PdfEncodings.IDENTITY_H,
                    PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);
        } catch (IOException e) {
            e.printStackTrace();
            // Обработка ошибки
        }
        //Создание изображения для Лого
        AssetManager assetManagerLogo = getAssets();
        InputStream inputStream = assetManagerLogo.open("logoKP1.png");
        ImageData imageDataLogo = ImageDataFactory.create(IOUtils.toByteArray(inputStream));
        Image imageLogo = new Image(imageDataLogo);

        // Установка размера изображения
        imageLogo.setWidth(200); // установка ширины в пунктах
        imageLogo.setHeight(80); // установка высоты в пунктах

        //добавляем таблицу лого в документ
        document.add(addTableLogo(imageLogo));

        document.add(new Paragraph(""));

        //добавляем таблицу с информацией о ГРАЗ и клиенте
        document.add(addTableInformComp());

        document.add(new Paragraph(""));

        //добавляем номер КП и дату
        document.add(new Paragraph(numberKP()).setFont(font));

        document.add(new Paragraph(""));

        //добавляем обращение
        document.add(new Paragraph("Уважаемые Господа!").setFont(fontBold).setTextAlignment(TextAlignment.CENTER).setFontSize(14));

        Paragraph paragraphObr = new Paragraph("Предлагаем Вам рассмотреть коммерческое предложение на поставку полуприцепа-цистерны " +
                "производства АО «Завод ГРАЗ»").setFont(font);

        paragraphObr.setFirstLineIndent(50);

        document.add(paragraphObr);

        document.add(new Paragraph(""));

        document.add(new Paragraph("Полуприцеп-цистерна мод. " + initViewPosition.getModelPPC())
                .setFont(fontBold).setTextAlignment(TextAlignment.CENTER).setFontSize(14));

        document.add(new Paragraph(""));

        //добавляем картинку ППЦ
        Image imageModel = loadImageFromAssets(this);

        imageModel.setWidth(450); // установка ширины в пунктах
        imageModel.setHeight(200); // установка высоты в пунктах

        imageModel.setHorizontalAlignment(HorizontalAlignment.CENTER);


        document.add(imageModel);

        document.add(new Paragraph(""));

        // Добавляем основную таблицу комлпектаций
        float[] columnWidths = {1, 6, 7}; // Здесь вы можете задать ширину каждой колонки
        Table table = new Table(UnitValue.createPercentArray(columnWidths)).useAllAvailableWidth(); // Использовать всю доступную ширину


        table.addHeaderCell(new Cell().add(new Paragraph("№").setFont(font)
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)));
        table.addHeaderCell(new Cell().add(new Paragraph("Наименование").setFont(font)
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)));
        table.addHeaderCell(new Cell().add(new Paragraph("Значение").setFont(font)
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)));

        // Указываем таблице пропустить отображение заголовков на последующих страницах
        table.setSkipFirstHeader(false);
        table.setSkipLastFooter(true);



        for(DataVisualPair dataVisualPair: initViewPosition.getListKP()){

            table.addCell(new Cell().add(new Paragraph(String.valueOf(dataVisualPair.getId()))
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE))); // Номер строки
            table.addCell(new Cell().add(new Paragraph(dataVisualPair.getName()).setFont(font)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)));
            table.addCell(new Cell().add(new Paragraph(dataVisualPair.getValue()).setFont(font)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)));

        }

        document.add(table);
        document.add(new Paragraph(""));

        //Добавляем таблицу со специальными опциями
        if(initViewPosition.getChoosSpecialLightOptionsList().size()>=1){
            flagSO = false;
            int number =1;

            float maxWidth = 0;

            document.add(new Paragraph("Дополнительные опции").setFont(font).setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(14));

            document.add(new Paragraph(""));

            // создаем таблицу
            float[] columnWidthsSO = {1, 6};
            Table tableSO = new Table(UnitValue.createPercentArray(columnWidthsSO)).useAllAvailableWidth();


            tableSO.addHeaderCell(new Cell().add(new Paragraph("№").setFont(font)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)));
            tableSO.addHeaderCell(new Cell().add(new Paragraph("Наименование").setFont(font)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)));

            for(SpecialLightOptions s: initViewPosition.getChoosSpecialLightOptionsList()){

                tableSO.addCell(new Cell().add(new Paragraph(String.valueOf(number))
                        .setTextAlignment(TextAlignment.CENTER)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE))); // Номер строки
                tableSO.addCell(new Cell().add(new Paragraph(s.getName()).setFont(font)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)));


                ++number;

            }

            document.add(tableSO);

            totalPages = pageBreakHandler.getPageCount();
            //добавляем разрыв страницы если доп. опций меньше 3
            if(initViewPosition.getChoosSpecialLightOptionsList().size()<4
            &&!initViewPosition.getType().equals("ППЦБ")&&totalPages==0){
                Log.d("totalPages", String.valueOf(totalPages));
                document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            }

        }


        //форматируем цену
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.FRANCE);
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        totalPriceKp = initViewPosition.getTotalPrice()
                +initViewPosition.getPlusPricePpc()+initViewPosition.getDeliveryCost();
        String formattedCost = String.format(Locale.FRANCE, "%,.2f", totalPriceKp);
        String validformattedCost = formattedCost.replace("\u00A0", " ").replace("\u202F", " ") + " руб.";



       // Устанавливаем интервал после абзаца
        float spacing = 1.25f;
        float spacingPrice = 1.0f;

        document.add(new Paragraph(""));

       //Добавляем поле стоимости
        Paragraph paragraphPrice = new Paragraph();
        paragraphPrice.add(new Text("Стоимость: ").setFont(font).setFontSize(14));
        paragraphPrice.add(new Text(validformattedCost + " с НДС 20%").setFont(fontBold).setFontSize(18));
        paragraphPrice.setMarginBottom(spacingPrice);
        document.add(paragraphPrice);

        //Добавляем срок поставки
        if(initViewPosition.getDeliveryTime()>0) {
            Paragraph paragraphDeliveryTime = new Paragraph();
            paragraphDeliveryTime.add(new Text("Срок поставки (календ. дней): ").setFont(font));
            paragraphDeliveryTime.add(new Text(String.valueOf(initViewPosition.getDeliveryTime()))
                    .setFont(fontBold).setFontSize(18));
            paragraphDeliveryTime.setMarginBottom(spacing);
            document.add(paragraphDeliveryTime);
        }

        //Добавляем оплату
        Paragraph paragraphPrepaid = new Paragraph();
        paragraphPrepaid.add(new Text("Оплата: ").setFont(font));
        paragraphPrepaid.add(new Text(String.valueOf(initViewPosition.getPrepaidPpc()) + "% аванс, ")
                .setFont(fontBold).setFontSize(14));
        if(!initViewPosition.isTimePay()){
            paragraphPrepaid.add(new Text(String.valueOf(100-initViewPosition.getPrepaidPpc())
                    +"% по факту готовности к отгрузке")).setFont(fontBold).setFontSize(14);
        }else{
            paragraphPrepaid.add(new Text(String.valueOf(100-initViewPosition.getPrepaidPpc())
                    +"% в течении "+initViewPosition.getTimePayDay()+" дней после отгрузки")).setFont(fontBold).setFontSize(14);
        }
        paragraphPrepaid.setMarginBottom(spacing);
        document.add(paragraphPrepaid);

        //Добавляем место отгрузки
        Paragraph paragraphDeliveryAdress = new Paragraph();
        if(!initViewPosition.isDeliveryPpc()){
            paragraphDeliveryAdress.add(new Text("Место отгрузки: Пензенская обл., Бессоновский район, с. Грабово, ул. Кирпичная 58").setFont(font));
        }else{
            paragraphDeliveryAdress.add(new Text("Доставим по адресу: "+initViewPosition.getDeliveryAdress()).setFont(font));
        }
        paragraphDeliveryAdress.setMarginBottom(spacing);
        document.add(paragraphDeliveryAdress);

        //срок действия коммерческого предожения
        if(initViewPosition.getDayValidity()>0) {
            Paragraph paragraphDayValidity = new Paragraph();
            paragraphDayValidity.add(new Text("*- срок действия предложения календ. дней: "
                    + initViewPosition.getDayValidity()).setFont(fontItalic));
            paragraphDayValidity.setMarginBottom(spacing);
            document.add(paragraphDayValidity);
        }

        totalPages = pageBreakHandler.getPageCount();
        //добавляем разрыв страницы если нет доп опций и это не битумовоз
        if(flagSO&&!initViewPosition.getType().equals("ППЦБ")&&totalPages==0){
            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        }

        Paragraph paragraphUserInfo = new Paragraph();
        paragraphUserInfo.setMultipliedLeading(1.0f);

        paragraphUserInfo.add(new Text("С уважением,\n").setFont(fontBold));
        if(!title.isEmpty()){
            paragraphUserInfo.add(new Text(title+"\n").setFont(fontBold));
        }
        if(!name.trim().isEmpty()){
            paragraphUserInfo.add(new Text(name+"\n").setFont(fontBold));
        }
        if(!nameName.isEmpty()){
            paragraphUserInfo.add(new Text(nameName+"\n").setFont(fontBold));
        }
        if(!surname.trim().isEmpty()){
            paragraphUserInfo.add(new Text(surname+"\n").setFont(fontBold));
        }
        if(!phoneNumber.isEmpty()){
            paragraphUserInfo.add(new Text(phoneNumber+"\n").setFont(fontBold));
        }
        if(!email.isEmpty()){
            paragraphUserInfo.add(new Text(email+"\n").setFont(fontBold));
        }

        document.add(paragraphUserInfo);






        document.close();

        return byteArrayOutputStream.toByteArray();
    }

    public Image loadImageFromAssets(Context context) {

        AssetManager assetManager = context.getAssets();

         //определяем тип ППЦ
        if(initViewPosition.getType().equals("ППЦА круг")){//определяем тип ППЦ

            if(initViewPosition.getTypeAxelCount().equals("3 оси")){//опред осевую формулу

                if(initViewPosition.getTypeGorlovin().equals("ЕВРО")){ // опред тип горловин
                // выбираем фото без горловин
                    try {
                        InputStream inputStream = assetManager.open("ppca_CircleEvro_3.jpg");
                        ImageData imageData = ImageDataFactory.create(IOUtils.toByteArray(inputStream));
                        return new Image(imageData);
                    } catch (Exception e) {
                        e.printStackTrace();
                        // В случае исключения возвращаем null или обрабатываем ошибку по-другому
                        return null;
                    }

                }else{

                    // выбираем фото с горловинами
                    try {
                        InputStream inputStream = assetManager.open("ppca_CircleGost_3.jpg");
                        ImageData imageData = ImageDataFactory.create(IOUtils.toByteArray(inputStream));
                        return new Image(imageData);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }

                }

            }else if(initViewPosition.getTypeAxelCount().equals("4 оси")){//опред осевую формулу

                if(initViewPosition.getTypeGorlovin().equals("ЕВРО")){ // опред тип горловин

                    // выбираем фото без горловин
                    try {
                        InputStream inputStream = assetManager.open("ppca_CircleEvro_4.jpg");
                        ImageData imageData = ImageDataFactory.create(IOUtils.toByteArray(inputStream));
                        return new Image(imageData);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }


                }else{

                    // выбираем фото с горловинами
                    try {
                        InputStream inputStream = assetManager.open("ppca_CircleGost_4.png");
                        ImageData imageData = ImageDataFactory.create(IOUtils.toByteArray(inputStream));
                        return new Image(imageData);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }

                }

            }else {

                // выбираем фото 3+1 (всегда без горловин
                try {
                    InputStream inputStream = assetManager.open("ppca_CircleEvro_3_1.jpg");
                    ImageData imageData = ImageDataFactory.create(IOUtils.toByteArray(inputStream));
                    return new Image(imageData);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }


        //определяем тип ППЦ
        } else if (initViewPosition.getType().equals("ППЦС круг")) {

            //определяем осевую формулу
            if(initViewPosition.getTypeAxelCount().equals("3 оси")){

                //Проверяем тип горловин
                if(initViewPosition.getTypeGorlovin().equals("ЕВРО")){

                    try {
                        InputStream inputStream = assetManager.open("ppc_CircleEvro_3.jpg");
                        ImageData imageData = ImageDataFactory.create(IOUtils.toByteArray(inputStream));
                        return new Image(imageData);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                // горловины ГОСТ
                }else{

                    try {
                        InputStream inputStream = assetManager.open("ppc_CircleGost_3.png");
                        ImageData imageData = ImageDataFactory.create(IOUtils.toByteArray(inputStream));
                        return new Image(imageData);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            // 4 оси
            }else{
                //Проверяем тип горловин
                if(initViewPosition.getTypeGorlovin().equals("ЕВРО")){
                    try {
                        InputStream inputStream = assetManager.open("ppc_CircleEvro_4.jpg");
                        ImageData imageData = ImageDataFactory.create(IOUtils.toByteArray(inputStream));
                        return new Image(imageData);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                // горловины ГОСТ
                }else{
                    try {
                        InputStream inputStream = assetManager.open("ppc_CircleGost_4.png");
                        ImageData imageData = ImageDataFactory.create(IOUtils.toByteArray(inputStream));
                        return new Image(imageData);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
            //определяем тип ППЦ
        }else if (initViewPosition.getType().equals("ППЦЧемотасс")){

            if(initViewPosition.getTypeGorlovin().equals("ЕВРО")){
                try {
                    InputStream inputStream = assetManager.open("chemotass_evro.png");
                    ImageData imageData = ImageDataFactory.create(IOUtils.toByteArray(inputStream));
                    return new Image(imageData);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
                // горловины ГОСТ
            }else{
                try {
                    InputStream inputStream = assetManager.open("chemotass_gost.png");
                    ImageData imageData = ImageDataFactory.create(IOUtils.toByteArray(inputStream));
                    return new Image(imageData);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            // Чемодан
        }else if(initViewPosition.getType().equals("Чемодан")){
            //объем до 33
            if(initViewPosition.getVolumePPC()<33){
                try {
                    InputStream inputStream = assetManager.open("chemodan_28.png");
                    ImageData imageData = ImageDataFactory.create(IOUtils.toByteArray(inputStream));
                    return new Image(imageData);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
                // объем 45
            } else if (initViewPosition.getVolumePPC()==45) {
                try {
                    InputStream inputStream = assetManager.open("chemodan_45.png");
                    ImageData imageData = ImageDataFactory.create(IOUtils.toByteArray(inputStream));
                    return new Image(imageData);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
                //объем от 33 до 42
            }else{

                // тип подвески
                if(initViewPosition.getTypeAxel().equals("Пневматическая")){
                    try {
                        InputStream inputStream = assetManager.open("chemodan33-42_1sk.png");
                        ImageData imageData = ImageDataFactory.create(IOUtils.toByteArray(inputStream));
                        return new Image(imageData);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }

                } else if (initViewPosition.getTypeAxel()
                        .equals("Пневматическая (2 ската)")) {
                    try {
                        InputStream inputStream = assetManager.open("chemodan33-42_2sk.png");
                        ImageData imageData = ImageDataFactory.create(IOUtils.toByteArray(inputStream));
                        return new Image(imageData);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                    //комбинированная подвеска
                }else{
                    try {
                        InputStream inputStream = assetManager.open("chemodan35-42_komb.png");
                        ImageData imageData = ImageDataFactory.create(IOUtils.toByteArray(inputStream));
                        return new Image(imageData);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
            //оставшийся тип ППЦБ
        }else{

            if(initViewPosition.getTypeAxelCount().equals("3 оси")){//опред осевую формулу

                try {
                    InputStream inputStream = assetManager.open("ppcb_3.png");
                    ImageData imageData = ImageDataFactory.create(IOUtils.toByteArray(inputStream));
                    return new Image(imageData);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }



            }else if(initViewPosition.getTypeAxelCount().equals("4 оси")){//опред осевую формулу


                try {
                    InputStream inputStream = assetManager.open("ppcb_4.jpg");
                    ImageData imageData = ImageDataFactory.create(IOUtils.toByteArray(inputStream));
                    return new Image(imageData);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }


            }else {

                // выбираем фото 3+1 (всегда без горловин
                try {
                    InputStream inputStream = assetManager.open("ppcb_3_1_1.png");
                    ImageData imageData = ImageDataFactory.create(IOUtils.toByteArray(inputStream));
                    return new Image(imageData);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }





        }

    }

    private Table addTableLogo(Image imageLogo) {

        String logoString1 = "АКЦИОНЕРНОЕ ОБЩЕСТВО";
        String logoString2 = "ГРАБОВСКИЙ АВТОМОБИЛЬНЫЙ ЗАВОД";
        String logoString3 = "АО «Завод ГРАЗ»";


        // Создание таблицы для лого
        float[] columnWidthsInformComp = {4,5}; // ширинf каждой колонки
        Table tableInformComp = new Table(UnitValue.createPercentArray(columnWidthsInformComp))
                .useAllAvailableWidth();

        // Создание ячейки для изображения
        Cell imageCellLogo = new Cell()
                .add(imageLogo.setAutoScale(true)) // Автомасштабирование изображения для подгонки размеров ячейки
                .setHorizontalAlignment(HorizontalAlignment.CENTER); // Выравнивание изображения в ячейке по центру

// Установка границы ячейки как прозрачной
        imageCellLogo.setBorder(Border.NO_BORDER);


        // создание записи для ячейки с текстовым лого
        Cell textCellLogo = new Cell();

// Первый параграф
        Paragraph para1 = new Paragraph(logoString1)
                .setFont(fontBold)
                .setFontSize(12);
        textCellLogo.add(para1);

// Второй параграф
        Paragraph para2 = new Paragraph(logoString2)
                .setFont(fontBold)
                .setFontSize(10);
        textCellLogo.add(para2);

// Третий параграф
        Paragraph para3 = new Paragraph(logoString3)
                .setFont(fontBold)
                .setFontSize(18);
        textCellLogo.add(para3);

// Установка выравнивания для ячейки текст лого
        textCellLogo.setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setBorder(Border.NO_BORDER);


        tableInformComp.addCell(imageCellLogo);
        tableInformComp.addCell(textCellLogo);


        return  tableInformComp;


    }

    private Table addTableInformComp() {

        String informCompString = "ИНН 5809036360/КПП 580901001\n" +
                "ОГРН1035800900816\n" +
                "442770, Пензенская обл., Бессоновский р-н, \n" +
                "c. Грабово, ул.Кирпичная, 58\n" +
                "тел.(8412) 32-99-84\n" +
                "факс.(8412) 45-83-43\n" +
                "E-mail: secretar@graz.sura.ru\n";



        // Создание таблицы для лого
        float[] columnWidthsInformComp = {4,5}; // ширинf каждой колонки
        Table tableInformComp = new Table(UnitValue.createPercentArray(columnWidthsInformComp))
                .useAllAvailableWidth();

        // создание записи для ячейки с текстом ГРАЗ
        Cell textCellGraz = new Cell();

      // Первый параграф
        Paragraph para1 = new Paragraph(informCompString)
                .setFont(font)
                .setFontSize(8);

        textCellGraz.add(para1);

        // Установка выравнивания для ячейки текст лого
        textCellGraz.setTextAlignment(TextAlignment.LEFT)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setBorder(Border.NO_BORDER);

        // создание записи для ячейки с текстом ГРАЗ
        Cell textCellClient = new Cell();

        // Первый параграф
        Paragraph para2 = new Paragraph(initViewPosition.getClient())
                .setFont(fontBold)
                .setFontSize(14);

        textCellClient.add(para2);

        textCellClient.setTextAlignment(TextAlignment.RIGHT)
                .setVerticalAlignment(VerticalAlignment.TOP)
                .setBorder(Border.NO_BORDER);

        tableInformComp.addCell(textCellGraz);
        tableInformComp.addCell(textCellClient);

        return  tableInformComp;

    }

    private String numberKP(){




        //форматируем дату и время. Для отслеживания входа юзеров
        String formattedDay;
        String formattedYear;
        int num = 1;



        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/Moscow"));


            DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            formattedDay = now.format(formatterDay);

            DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("yyyy");
            formattedYear = now.format(formatterYear);


        } else {


            SimpleDateFormat formatterDay = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            formatterDay.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
            formattedDay = formatterDay.format(Calendar.getInstance().getTime());

            SimpleDateFormat formatterYear = new SimpleDateFormat("yyyy", Locale.getDefault());
            formatterYear.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
            formattedYear = formatterYear.format(Calendar.getInstance().getTime());


        }

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        String dataStart = sharedPreferences.getString("дата","0");

        if(dataStart.equals("0")){
            myEdit.putString("дата", formattedYear);
            myEdit.putInt("номер",1);
            myEdit.apply();
        }

       dataStart = sharedPreferences.getString("дата","0");

        int intDataStart = Integer.parseInt(dataStart);
        int intFormattedYear = Integer.parseInt(formattedYear);

        if(intDataStart<intFormattedYear){

            myEdit.putInt("номер",1);
            myEdit.putString("дата", formattedYear);
            myEdit.apply();

        }else{
            num = sharedPreferences.getInt("номер", 1);
            num +=1;
            initViewPosition.setNumberKP(num);
            myEdit.putInt("номер",num);
            myEdit.apply();
        }

        String answer = "Исх. № " + num +" "+ name.charAt(0) + surname.charAt(0) + " от " + formattedDay;

        initViewPosition.setDateKp(formattedDay);

        return answer;



    }



    private void savePDFToDownloads(String fileName, byte[] pdfContent) throws IOException {
        ContentResolver resolver = getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf");
        contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS);

        uri = resolver.insert(MediaStore.Files.getContentUri("external"), contentValues);
        initViewPosition.setUriPdfKp(uri);
        initViewPosition.setCurrentPageKp(0);

        if (uri != null) {
            try (OutputStream outputStream = resolver.openOutputStream(uri)) {
                assert outputStream != null;
                outputStream.write(pdfContent);
            }
            Toast.makeText(this, "PDF сохранен в Downloads", Toast.LENGTH_LONG).show();
        } else {
            throw new IOException("Не удалось создать файл в Downloads");
        }


    }

    private void sharePdf(){

        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("application/pdf");
        share.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(share, "Share PDF"));

    }




}