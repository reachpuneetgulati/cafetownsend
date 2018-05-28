package common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LabelValue
{
    private String label;
    private String value;

    public LabelValue(@JsonProperty("label") final String label, @JsonProperty("value") final String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return this.label;
    }

    public String getValue() {
        return this.value;
    }
}
