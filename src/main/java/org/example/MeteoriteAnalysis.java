package org.example;

public class MeteoriteAnalysis {
    public static void main(String[] args) {
        Pipeline pipeline = new Pipeline("test_config.txt");
        pipeline.process("input.json", "test_output.json");
    }
}
