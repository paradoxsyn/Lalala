package dj.rabithole.losu;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Rabithole on 11/18/2016.
 */

public class WebViewController extends WebViewClient {

    @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

}
