package com.easyray.baseapi.init;

import org.springframework.boot.ApplicationArguments;

import java.io.IOException;

/**
 * @Date: 20-2-2
 * @Author: wyy
 */
public interface IEasyIniter extends Comparable<IEasyIniter> {

    public int getOrder();

    public void init(ApplicationArguments args) throws IOException;

    @Override
    default int compareTo(IEasyIniter o) {
        return Integer.compare(getOrder(), o.getOrder());
    }

}
