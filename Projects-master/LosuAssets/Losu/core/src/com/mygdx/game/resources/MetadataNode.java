package com.mygdx.game.resources;

/**
 * Created by Paradox on 3/14/2017.
 */
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "metadata")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MetadataNode")
class MetadataNode {
    int format;

    @XmlElement(name = "ptm_ratio")
    float ptmRatio;
}
