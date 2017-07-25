package com.ge.med.mobile.nursing.utils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Qu on 2016/12/21.
 */
public class SerializableMap implements Serializable{
        private Map map;
        public Map getMap()
        {
            return map;
        }
        public void setMap(Map map)
        {
            this.map=map;
        }
}
