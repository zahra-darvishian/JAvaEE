package GithubTest;

import ir.bki.GsonModel;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created byz-darvishian on 2018/04/09.
 */
@XmlRootElement
public class PipelineDto extends GsonModel{
    private String payload;
    private String tag;

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
