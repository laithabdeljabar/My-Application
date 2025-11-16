package your.package

import android.app.Activity
import android.os.Bundle
import com.example.kotlin_text_screen.R

class TextProcessorActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout for this activity (defined in res/layout/activity_text_processor.xml)
        setContentView(R.layout.activity_text_processor)
    }
}



