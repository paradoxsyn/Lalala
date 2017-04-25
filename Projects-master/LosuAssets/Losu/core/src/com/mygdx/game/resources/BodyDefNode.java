package com.mygdx.game.resources;

/**
 * Created by Paradox on 3/14/2017.
 */

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "bodydef")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BodyDefNode")
class BodyDefNode {
    @XmlElement(required = true)
    BodiesNode bodies;

    MetadataNode metadata;

    @XmlAttribute(name = "version")
    @SuppressWarnings("unused")
    String version;
}

