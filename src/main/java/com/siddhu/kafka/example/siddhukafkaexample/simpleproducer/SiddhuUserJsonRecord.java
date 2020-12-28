
package com.siddhu.kafka.example.siddhukafkaexample.simpleproducer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "housenumber",
    "societyname",
    "pinnumber"
})
public class SiddhuUserJsonRecord {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("housenumber")
    private Double housenumber;
    @JsonProperty("societyname")
    private String societyname;
    @JsonProperty("pinnumber")
    private Integer pinnumber;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public SiddhuUserJsonRecord withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public SiddhuUserJsonRecord withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("housenumber")
    public Double getHousenumber() {
        return housenumber;
    }

    @JsonProperty("housenumber")
    public void setHousenumber(Double housenumber) {
        this.housenumber = housenumber;
    }

    public SiddhuUserJsonRecord withHousenumber(Double housenumber) {
        this.housenumber = housenumber;
        return this;
    }

    @JsonProperty("societyname")
    public String getSocietyname() {
        return societyname;
    }

    @JsonProperty("societyname")
    public void setSocietyname(String societyname) {
        this.societyname = societyname;
    }

    public SiddhuUserJsonRecord withSocietyname(String societyname) {
        this.societyname = societyname;
        return this;
    }

    @JsonProperty("pinnumber")
    public Integer getPinnumber() {
        return pinnumber;
    }

    @JsonProperty("pinnumber")
    public void setPinnumber(Integer pinnumber) {
        this.pinnumber = pinnumber;
    }

    public SiddhuUserJsonRecord withPinnumber(Integer pinnumber) {
        this.pinnumber = pinnumber;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SiddhuUserJsonRecord.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("housenumber");
        sb.append('=');
        sb.append(((this.housenumber == null)?"<null>":this.housenumber));
        sb.append(',');
        sb.append("societyname");
        sb.append('=');
        sb.append(((this.societyname == null)?"<null>":this.societyname));
        sb.append(',');
        sb.append("pinnumber");
        sb.append('=');
        sb.append(((this.pinnumber == null)?"<null>":this.pinnumber));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
