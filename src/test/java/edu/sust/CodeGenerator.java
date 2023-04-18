package edu.sust;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * 代码生成器
 */
public class CodeGenerator {
    public static void main(String[] args) {
        String moduleName = "drug";
        String mapperLocation = "C:\\Users\\85370\\IdeaProjects\\Treatment-Medicine-Web\\src\\main\\resources\\mapper\\" + moduleName;
        String tables = "x_drug,x_product";
        FastAutoGenerator.create("jdbc:mysql:///stsm_db", "root", "123")
                .globalConfig(builder -> {
                    builder.author("ylc") // 设置作者
                           // .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\Users\\85370\\IdeaProjects\\Treatment-Medicine-Web\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("edu.sust") // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables) // 设置需要生成的表名
                            .addTablePrefix("x_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
