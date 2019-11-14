package com.codingapi.deeplearning.demo02.learn;

import lombok.extern.slf4j.Slf4j;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * 梯度下降算法
 * @author lorne
 * @date 2019-10-22
 */
@Slf4j
public class GradientDescentAlgorithmFunction {

    /**
     * 学习率α
     */
    private double alpha;

    /**
     * 假如函数参数
     */
    private INDArray thetaTemp;

    /**
     * 训练次数
     */
    private int batch;

    private INDArray x,y;


    public GradientDescentAlgorithmFunction(double alpha,int batch,INDArray array) {
        this.alpha = alpha;
        this.batch = batch;
        this.thetaTemp = Nd4j.rand(array.columns()-1,1);    //[2,1]
        this.x =  array.getColumns(0,1);//[2,100]
        this.y = array.getColumns(2);//[1,100]

        log.info("thetaTemp->shape:{},x->shape:{},y->shape:{}",
                thetaTemp.shape(),x.shape(),y.shape());
    }

    /**
     * 基于矩阵的 梯度下降算法
     */
    public void train(){
        for(int i=0;i<batch;i++){
            INDArray gradient = gradient(x,y);
            //theta 赋值
            thetaTemp = thetaTemp.sub(gradient.mul(alpha));

            log.info("train count {},params:{}",i,thetaTemp);

        }
        log.info("train over params:{}",thetaTemp);
    }


    private INDArray gradient(INDArray x,INDArray y){
        double m = y.columns();
        INDArray gradient = Nd4j.create(x.columns(),1);//[2,1]
        INDArray error = error(x,y);//[100,1]
        for(int j=0;j<x.columns();j++) {
            //
            gradient.putScalar(j,Nd4j.sum(error.mul(x.getColumn(j))).div(m).sumNumber().doubleValue());
        }
        return gradient;
    }


    private INDArray error(INDArray x,INDArray y){
        return hypothesisFunction(x).sub(y);
    }

    private INDArray hypothesisFunction(INDArray x){
        //[100,2] * [2,1]
        return x.mmul(thetaTemp);//[100,1]
    }

}
