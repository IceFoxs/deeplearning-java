package com.codingapi.deeplearning.demo06.learn;

import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * @author lorne
 * @date 2019-11-15
 * @description
 */
public interface LossFunction {

    /**
     * 获取损失函数得分
     * @param predict   预测值
     * @param y         实际值
     * @return          得分值
     */
    INDArray score(INDArray predict, INDArray y);

}