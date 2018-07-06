package co.unir.oscardo.kenapp.Constantes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tiempo {

    private SimpleDateFormat databaseDateTimeFormate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private SimpleDateFormat databaseDateFormate = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MM.yy");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
    private SimpleDateFormat sdf3 = new SimpleDateFormat("EEE, MMM d, ''yy");
    private SimpleDateFormat sdf4 = new SimpleDateFormat("h:mm a");
    private SimpleDateFormat sdf5 = new SimpleDateFormat("h:mm");
    private SimpleDateFormat sdf6 = new SimpleDateFormat("H:mm:ss:SSS");
    private SimpleDateFormat sdf7 = new SimpleDateFormat("K:mm a,z");
    private SimpleDateFormat sdf8 = new SimpleDateFormat("yyyy.MMMMM.dd GGG hh:mm aaa");

    private String currentDateandTime = databaseDateTimeFormate.format(new Date());     //2009-06-30 08:29:36
    private String currentTime = sdf5.format(new Date());     //8:29
    private String currentDate_punto = sdf1.format(new Date());     //30.06.09

    private String currentDate = databaseDateFormate.format(new Date());     //2009-06-30
    private String formato_ddMMyy_punto = sdf1.format(new Date());     //30.06.09
    private String formato_yyyyMMdd_hora = sdf2.format(new Date());     //2009.06.30 AD at 08:29:36 PDT
    private String formato_dia_mmdd_yy = sdf3.format(new Date());     //Tue, Jun 30, '09
    private String formato_hmm_a = sdf4.format(new Date());     //8:29 PM
    private String formato_hmm = sdf5.format(new Date());     //8:29
    private String formato_hmmss_sss = sdf6.format(new Date());     //8:28:36:249
    private String formato_hmm_az = sdf7.format(new Date());     //8:29 AM,PDT
    private String formato_yyyyMMdd_ggg_hhmmaaa_a = sdf8.format(new Date());     //2009.June.30 AD 08:29 AM

    public String getCurrentDateandTime() {
        return currentDateandTime;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public String getCurrentDate_punto() {
        return currentDate_punto;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public String getFormato_ddMMyy_punto() {
        return formato_ddMMyy_punto;
    }

    public String getFormato_yyyyMMdd_hora() {
        return formato_yyyyMMdd_hora;
    }

    public String getFormato_dia_mmdd_yy() {
        return formato_dia_mmdd_yy;
    }

    public String getFormato_hmm_a() {
        return formato_hmm_a;
    }

    public String getFormato_hmm() {
        return formato_hmm;
    }

    public String getFormato_hmmss_sss() {
        return formato_hmmss_sss;
    }

    public String getFormato_hmm_az() {
        return formato_hmm_az;
    }

    public String getFormato_yyyyMMdd_ggg_hhmmaaa_a() {
        return formato_yyyyMMdd_ggg_hhmmaaa_a;
    }
}
