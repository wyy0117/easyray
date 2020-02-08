package com.easyray.baseapi.init;

import org.springframework.boot.ApplicationArguments;

/**
 * @Date: 20-2-2
 * @Author: wyy
 */
public interface IEasyInit extends Comparable<IEasyInit> {

    public int getOrder();

    public void init(ApplicationArguments args) throws Exception;

    @Override
    default int compareTo(IEasyInit o) {
        return Integer.compare(getOrder(), o.getOrder());
    }

}
