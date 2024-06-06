package org.example;

public class MeteoriteAnalysis {
    public static void main(String[] args) {
        Pipeline pipeline = new Pipeline("config.txt");
        pipeline.process("input.json", "output.json");
    }
}
