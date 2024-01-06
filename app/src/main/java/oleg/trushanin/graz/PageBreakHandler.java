package oleg.trushanin.graz;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;

public class PageBreakHandler implements IEventHandler {


    private int pageCount = 0;
    @Override
    public void handleEvent(Event event) {
        pageCount++;
    }


    public int getPageCount() {
        return pageCount;
    }

}
