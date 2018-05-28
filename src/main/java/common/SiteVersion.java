package common;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;

public class SiteVersion {
    private int version;
    private ArrayList<LabelValue> dateFormats;
    private ArrayList<LabelValue> byConstants;

    public SiteVersion(@JsonProperty("version") int version, @JsonProperty("dateFormats") ArrayList<LabelValue> dateFormats,@JsonProperty("byConstants") ArrayList<LabelValue> byConstants) {
        this.version = version;
        this.dateFormats = dateFormats;
        this.byConstants = byConstants;
    }

    public static Integer getBestIndexFromList(int version, ArrayList<SiteVersion> versions) {

        final ArrayList<SiteVersion> vs = new ArrayList<SiteVersion>(versions);
        Collections.reverse(vs);
        int index = vs.size() - 1;
        for (int i = 0; i < vs.size(); ++i) {
            if (vs.get(i).version <= version) {
                index = i;
                break;
            }
        }
        return versions.indexOf(vs.get(index));
    }

    public int getVersion() {
        return version;
    }

    public ArrayList<LabelValue> getDateFormats() {
        return dateFormats;
    }

    public ArrayList<LabelValue> getByConstants() {
        return byConstants;
    }

    public String getByConstant(final String name){
        for (final LabelValue by : this.byConstants) {
            if (by.getLabel().equals(name)) {
                return by.getValue();
            }
        }
        return null;
    }

    public String getDateFormat(final String name){
        for (final LabelValue by : this.dateFormats) {
            if (by.getLabel().equals(name)) {
                return by.getValue();
            }
        }
        return null;
    }
}
