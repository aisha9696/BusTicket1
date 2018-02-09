/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busticket1;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author User
 */
public class DateItem {
    
    private Date mDate;

    public DateItem(Date date) {
        mDate = date;
    }

    public Date getDate() {
        return mDate;
    }

    @Override
    public String toString() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(mDate);
    }
}