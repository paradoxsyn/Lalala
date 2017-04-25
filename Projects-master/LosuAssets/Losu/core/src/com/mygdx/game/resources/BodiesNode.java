package com.mygdx.game.resources;

/**
 * Created by Paradox on 3/14/2017.
 */

import java.util.List;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "body")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BodiesNode")
class BodiesNode {
    List<BodyNode> body;
}
