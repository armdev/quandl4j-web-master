package com.progress.web.auth;

import com.jimmoores.quandl.DataSetRequest;
import com.jimmoores.quandl.QuandlSession;
import com.jimmoores.quandl.Row;
import com.jimmoores.quandl.TabularResult;
import com.progress.frontend.cache.CacheHandler;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.threeten.bp.LocalDate;

/**
 *
 * @author armen.arzumanyan@gmail.com
 */
@ManagedBean(name = "quandlFinderBean")
@SessionScoped
public class QuandlFinderBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{cacheHandler}")
    private CacheHandler cacheHandler = null;
    private String simbol;
    private Date startDate;
    private List<Quandl> finalList = null;

    public QuandlFinderBean() {

    }

    @PostConstruct
    public void init() {
        finalList = new ArrayList<>();
    }

    public List<Quandl> getFinalList() {
        return finalList;
    }

    public void doClear() {
        finalList.clear();
    }

    public void setFinalList(List<Quandl> finalList) {
        this.finalList = finalList;
    }//AAPL2016-04-01

    private List<Quandl> findList(String simbol, String searchKey) {
        QuandlSession quandlSession = QuandlSession.create();
        Quandl quandl = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        dateFormat.format(startDate);
        String datesearch = dateFormat.format(cal.getTime());
        String[] aaa = datesearch.split("-");
        LocalDate RECENTISH_DATE = LocalDate.of(Integer.valueOf(aaa[0]), Integer.valueOf(aaa[1]), Integer.valueOf(aaa[2]));
        //  List<Quandl> cacheList = (List<Quandl>) cacheHandler.getQuandlListFromCache(searchKey);
        // if (cacheList == null) {
        //;
        try {
            TabularResult tabularResult = quandlSession.getDataSet(DataSetRequest.Builder.of("WIKI/" + simbol).withStartDate(RECENTISH_DATE).build());
            Iterator<Row> list = tabularResult.iterator();

            while (list.hasNext()) {
                Row r = list.next();
                quandl = new Quandl();
                // System.out.println("FOUND!!!! ");
                quandl.setDate(r.getString("Date"));
                quandl.setOpen(r.getString("Open"));
                quandl.setHigh(r.getString("High"));
                quandl.setLow(r.getString("Low"));
                if (r.getString("Close") != null) {
                    quandl.setClose(r.getString("Close"));
                }
                if (r.getString("Volume") != null) {
                    quandl.setVolume(r.getString("Volume"));
                }
                if (r.getString("Ex-Dividend") != null) {
                    quandl.setEx_Dividend(r.getString("Ex-Dividend"));
                }
                if (r.getString("Split Ratio") != null) {
                    quandl.setSplitRatio(r.getString("Split Ratio"));
                }
                if (r.getString("Adj. Open") != null) {
                    quandl.setAdjOpen(r.getString("Adj. Open"));
                }
                if (r.getString("Adj. High") != null) {
                    quandl.setAdjHigh(r.getString("Adj. High"));
                }
                if (r.getString("Adj. Low") != null) {
                    quandl.setAdjLow(r.getString("Adj. Low"));
                }
                if (r.getString("Adj. Close") != null) {
                    quandl.setAdjClose(r.getString("Adj. Close"));
                }
                if (r.getString("Adj. Volume") != null) {
                    quandl.setAdjVolume(r.getString("Adj. Volume"));
                }
                //  System.out.println("Open: " + quandl.getOpen());
                //  System.out.println("quandl: " + quandl);
                //  System.out.println("finalList: " + finalList);

                finalList.add(quandl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //}
        return finalList;
    }

    public void doCall() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);
            //dateFormat.format(startDate);
            String datesearch = dateFormat.format(cal.getTime());
            String uniqueKey = simbol + datesearch;

            System.out.println("#call uniqueKey " + uniqueKey);

            List<Quandl> cacheList = (List<Quandl>) cacheHandler.getQuandlListFromCache(uniqueKey);

            System.out.println("#Cache List  " + cacheList);

            if (cacheList != null && !cacheList.isEmpty()) {
                finalList = new ArrayList<>();
                finalList.addAll(cacheList);
                System.out.println("@@get list from cache, size is  " + cacheList.size());
            } else {
                System.out.println("#Cache List is NULL DO NEW CALL  ");
                finalList = this.findList(simbol, uniqueKey);
                if (!finalList.isEmpty()) {
                    System.out.println("New data founded: #Put into cache: size is : " + finalList.size());
                    System.out.println("#put uniqueKey " + uniqueKey);
                    cacheHandler.putQuandlListToCache(uniqueKey, finalList);
                    System.out.println("#Cache is null? call backend, get size  " + finalList.size());
                } else {
                    System.out.println("No fresh data from quandl");
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(QuandlFinderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getSimbol() {
        return simbol;
    }

    public void setSimbol(String simbol) {
        this.simbol = simbol;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setCacheHandler(CacheHandler cacheHandler) {
        this.cacheHandler = cacheHandler;
    }

}
