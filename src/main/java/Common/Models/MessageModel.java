package Common.Models;

import javax.xml.bind.annotation.*;
import java.text.DateFormat;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class MessageModel {
    @XmlAttribute
    private UserModel user;
    @XmlAttribute
    private DateFormat date;
    @XmlValue
    private String content;

    public MessageModel(UserModel user, DateFormat date, String content) {
        this.user = user;
        this.date = date;
        this.content = content;
    }

    public UserModel getUser() {
        return user;
    }

    public DateFormat getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }


}
