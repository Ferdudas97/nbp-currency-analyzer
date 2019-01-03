package pl.parser.nbp;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Position {
    @JacksonXmlProperty(localName = "nazwa_waluty")
    private String currencyName;
    @JacksonXmlProperty(localName = "przelicznik")
    private String conversionRate;
    @JacksonXmlProperty(localName = "kod_waluty")
    private String code;
    @JacksonXmlProperty(localName = "kurs_kupna")
    private String bid_rate;
    @JacksonXmlProperty(localName = "kurs_sprzedazy")
    private String sale_rate;
}
