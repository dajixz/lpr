package com.apsd.lpr.core;
import java.util.Vector;

import org.bytedeco.javacpp.opencv_core.Mat;

import com.apsd.lpr.core.CoreFunc.Color;

/**
 * 字符识别
 *
 * @author Perye
 *
 */
public class CharsRecognize {

    private CharsSegment charsSegment = new CharsSegment();

    private CharsIdentify charsIdentify = new CharsIdentify();

    public void loadANN(final String s) {
        charsIdentify.loadModel(s);
    }

    /**
     * 字符分割与识别
     *
     * @param plate 输入车牌
     * @return 车牌识别结果
     */
    public String charsRecognize(final Mat plate) {
        // 车牌字符图像集合
        Vector<Mat> matVec = new Vector<>();
        // 车牌识别结果
        String plateIdentify = "";

        int result = charsSegment.charsSegment(plate, matVec);
        if (0 == result) {
            for (int j = 0; j < matVec.size(); j++) {
                Mat charMat = matVec.get(j);
                // 默认首个字符块是中文字符
                String charcater = charsIdentify.charsIdentify(charMat, (0 == j), (1 == j));
                plateIdentify = plateIdentify + charcater;
            }
        }

        return plateIdentify;
    }

    /**
     * 是否开启调试模式
     *
     * @param isDebug
     */
    public void setCRDebug(final boolean isDebug) {
        charsSegment.setDebug(isDebug);
    }

    /**
     * 获取调试模式状态
     *
     * @return
     */
    public boolean getCRDebug() {
        return charsSegment.getDebug();
    }

    /**
     * 获得车牌颜色
     *
     * @param input
     * @return
     */
    public final String getPlateType(final Mat input) {
        String color = "未知";
        Color result = CoreFunc.getPlateType(input, true);
        if (Color.BLUE == result)
            color = "蓝牌";
        if (Color.YELLOW == result)
            color = "黄牌";
        return color;
    }

    /**
     * 设置柳丁大小变量
     *
     * @param param
     */
    public void setLiuDingSize(final int param) {
        charsSegment.setLiuDingSize(param);
    }

    /**
     * 设置颜色阈值
     *
     * @param param
     */
    public void setColorThreshold(final int param) {
        charsSegment.setColorThreshold(param);
    }

    /**
     * 设置蓝色百分比
     *
     * @param param
     */
    public void setBluePercent(final float param) {
        charsSegment.setBluePercent(param);
    }

    /**
     * 得到蓝色百分比
     *
     */
    public final float getBluePercent() {
        return charsSegment.getBluePercent();
    }

    /**
     * 设置白色百分比
     *
     * @param param
     */
    public void setWhitePercent(final float param) {
        charsSegment.setWhitePercent(param);
    }

    /**
     * 得到白色百分比
     *
     */
    public final float getWhitePercent() {
        return charsSegment.getWhitePercent();
    }

}