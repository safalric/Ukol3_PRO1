package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Action
{
    @SerializedName("denZkr")
    public String dayShort;

    @SerializedName("obsazeni")
    public Long studentsCount;

    @SerializedName("ucitIdno")
    public Long teacherId;
}
