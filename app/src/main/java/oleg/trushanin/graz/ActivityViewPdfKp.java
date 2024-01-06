package oleg.trushanin.graz;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;

public class ActivityViewPdfKp extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf_kp);
        InitViewPosition initViewPosition = InitViewPosition.getInstance();



        PDFView pdfView = findViewById(R.id.pdfViewKp);
        pdfView.fromUri(initViewPosition.getUriPdfKp()) // Замените на свой путь к файлу PDF
                .defaultPage(initViewPosition.getCurrentPageKp())
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        initViewPosition.setCurrentPageKp(page);
                    }
                })
                .load();

    }
}