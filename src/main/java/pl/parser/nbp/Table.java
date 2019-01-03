package pl.parser.nbp;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Table {
    @JacksonXmlProperty(localName = "numer_tabeli")
    private String table_number;
    @JacksonXmlProperty(localName = "data_notowania")
    private String notificationDate;
    @JacksonXmlProperty(localName = "data_publikacji")
    private String publiactionDate;
    @JacksonXmlProperty(localName = "pozycja")
    private ArrayList<Position> positions;

    @JacksonXmlProperty(localName = "typ")
    private String type;
    @JacksonXmlProperty(localName = "uid")
    private String uid;
}
