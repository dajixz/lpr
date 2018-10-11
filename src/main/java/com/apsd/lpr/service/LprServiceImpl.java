package com.apsd.lpr.service;

import com.apsd.lpr.core.CharsIdentify;
import com.apsd.lpr.core.CharsRecognize;
import com.apsd.lpr.core.CoreFunc;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.springframework.stereotype.Service;

import static com.apsd.lpr.core.CoreFunc.getPlateType;
import static org.bytedeco.javacpp.opencv_highgui.imread;

/**
 * @author Perye
 */
@Service
public class LprServiceImpl implements LprService{


    /**
     * 字符标识
     * @param imgPath
     * @return
     */
    @Override
    public String charsIndetify(String imgPath) {
        Mat src = imread(imgPath);
        CharsIdentify charsIdentify = new CharsIdentify();
        String result = charsIdentify.charsIdentify(src, false, true);
        return result;
    }

    /**
     * 字符识别
     * @param imgPath
     * @return
     */
    @Override
    public String charsRecognize(String imgPath) {
        Mat src = imread(imgPath);
        CharsRecognize charsRecognize = new CharsRecognize();
        charsRecognize.setCRDebug(true);
        String result = charsRecognize.charsRecognize(src);
        return result;
    }

    /**
     * 颜色检测
     * @param imgPath
     * @return
     */
    @Override
    public String colorDetect(String imgPath) {
        Mat src = imread(imgPath);
        CoreFunc.Color color = getPlateType(src, true);
        return color.toString();
    }
}
