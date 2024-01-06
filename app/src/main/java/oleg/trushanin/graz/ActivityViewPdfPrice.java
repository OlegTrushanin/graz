package oleg.trushanin.graz;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class ActivityViewPdfPrice extends AppCompatActivity {
    InitViewPosition initViewPosition;
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf_price);
        initViewPosition = InitViewPosition.getInstance();



        pdfView = findViewById(R.id.pdfViewPrice);
        pdfView.fromUri(initViewPosition.getUriPdfPrice()) // Замените на свой путь к файлу PDF
                .defaultPage(initViewPosition.getCurrentPagePrice())
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        initViewPosition.setCurrentPagePrice(page);
                    }
                })
                .enableAntialiasing(true) // Улучшает качество отображения на низких уровнях масштаба
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10) // Отступ между страницами
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {
                        pdfView.zoomTo(initViewPosition.getZoomLevelPricePdf()); // Восстанавливаем масштаб
                        pdfView.moveTo(initViewPosition.getOffsetXPricePdf(), initViewPosition.getOffsetYPricePdf()); // Восстанавливаем положение прокрутки
                    }
                })
                .load();

    }

    @Override
    protected void onPause() {
        super.onPause();
        initViewPosition.setZoomLevelPricePdf(pdfView.getZoom());
        initViewPosition.setOffsetXPricePdf(pdfView.getCurrentXOffset());
        initViewPosition.setOffsetYPricePdf(pdfView.getCurrentYOffset());

    }
}