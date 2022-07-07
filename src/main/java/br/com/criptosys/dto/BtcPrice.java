package br.com.criptosys.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class BtcPrice {

    private Ticker ticker;

}

@Data
class Ticker{

    private String high;
    private String low;
    private String vol;
    private String last;
    private String buy;
    private String sell;
    private String open;
    private Date date;
}
