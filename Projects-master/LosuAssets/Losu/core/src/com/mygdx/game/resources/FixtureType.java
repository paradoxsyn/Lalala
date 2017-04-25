package com.mygdx.game.resources;

/**
 * Created by Paradox on 3/14/2017.
 */

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "FixtureType")
@XmlEnum
enum FixtureType {
    POLYGON,
    CIRCLE;

    public String value() {
        return name();
    }

    public static FixtureType fromValue(String v) {
        return valueOf(v);
    }
}
