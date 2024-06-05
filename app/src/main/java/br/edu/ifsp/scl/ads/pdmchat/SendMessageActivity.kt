// SendMessageActivity.kt
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_send_message.*

class SendMessageActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_message)

        database = FirebaseDatabase.getInstance().reference.child("messages")

        buttonSend.setOnClickListener {
            val sender = editTextSender.text.toString()
            val message = editTextMessage.text.toString()
            val timestamp = System.currentTimeMillis()

            if (message.isNotEmpty() && message.length <= 150) {
                val newMessage = Message(sender, timestamp, message)
                database.push().setValue(newMessage)
                finish()
            }
        }
    }
}
