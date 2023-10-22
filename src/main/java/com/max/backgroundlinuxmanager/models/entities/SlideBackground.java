/*
 * The MIT License
 *
 * Copyright 2019 Maximiliano Fernández thebluemax13 at gmail.com.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.max.backgroundlinuxmanager.models.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Maximiliano Fernández thebluemax13 at gmail.com
 */
public class SlideBackground {

    private String name;

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setTransitionDuration(int transitionDuration) {
        this.transitionDuration = transitionDuration;
    }

    public void setImageDuration(int imageDuration) {
        this.imageDuration = imageDuration;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    private int transitionDuration;
    private int imageDuration;
    private Map<String, Integer> startTime;
    private final List<FrameBackground> frames;

    public String toString() {
        return "<background>" +
                getStartTime() +
                getFrameListToString() +
                "</background>";
    }

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    /**
     *
     */
    public SlideBackground() {
        super();
        transitionDuration = AppConfiguration.DEFAULT_TRANSITION_DURATION;
        frames = new ArrayList<>();
    }

    /**
     * @param timestamp
     */
    public void setStartTime(Map<String, Integer> timestamp) {
        startTime = timestamp;
    }

    public void setTransition(int duration) {
        transitionDuration = duration;
    }

    public int getTransitionDuration() {
        return transitionDuration;
    }

    public void setImageduration(int duration) {
        imageDuration = duration;
    }

    public int getImageDuration() {
        return imageDuration;
    }

    private int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    /**
     * @param fBG
     */
    public void addFrame(FrameBackground fBG) {

        if (!frames.isEmpty()) {
            FrameBackground first = frames.get(0);
            FrameBackground last = frames.get(frames.size() - 1);
            last.setPathTo(fBG.getFilePath());
            fBG.setPathTo(first.getFilePath());
        }
        frames.add(fBG);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    public String getFilename() {
        return name + ".xml";
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getMothToString() {
        return "<month>" + getMonth() + "</month>\n";
    }

    public String getMinuteToString() {
        return "<minute>" + getMinute() + "</minute>\n";
    }

    public String getDayToString() {
        return "<day>" + getDay() + "</day>\n";
    }

    public String getSecondToString() {
        return "<second>" + getSecond() + "</second>\n";
    }

    /**
     * @param name the name to set
     */

    /**
     * @return
     */
    public List<FrameBackground> getFrames() {
        return frames;
    }

    public Map<String, Integer> getTime() {
        return startTime;
    }

    public String getYearToString() {
        return "<year>" + getYear() + "</year>\n";
    }

    public String getHourToString() {
        return "<hour>" + getHour() + "</hour>\n";
    }

    public void clear() {
        frames.clear();
    }

    public String getStartTime() {
        return "<starttime>\n" +
                getYearToString() +
                getMothToString() +
                getDayToString() +
                getHourToString() +
                getMinuteToString() +
                getSecondToString() +
                "</starttime>";

    }

    public String getFrameListToString() {
        String list = "";
        for (int x = 0; x < frames.size(); x++) {
            frames.get(x).setDuration(this.imageDuration);
            frames.get(x).setDurationTransition(this.transitionDuration);
            list += frames.get(x).toString();
        }
        return list;
    }

}
