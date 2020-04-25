package my.test.process;

import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

import my.test.annotation.BindView;

/**
 * Created by YiVjay
 * on 2020/4/25
 */
@AutoService(Processor.class)
public class MyProcessor extends AbstractProcessor {
    //生成对象文件
    Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer=processingEnvironment.getFiler();
    }

    /**
     * 支持java源版本
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return super.getSupportedSourceVersion();
    }

    /**
     *声明注解处理器处理的注解
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> strings=new HashSet<>();
        strings.add(BindView.class.getCanonicalName());
        return strings;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        //根据注解获取到模块中的节点
        //类节点 TypeElement  成员变量节点 VariableElement  方法节点  ExecutableElement

        Set<?extends Element> elements=roundEnvironment.getElementsAnnotatedWith(BindView.class);
        Map<String,String > map=new HashMap<>();
        for (Element element : elements) {
            TypeElement typeElement= (TypeElement) element;
            //类名
            String className = typeElement.getQualifiedName().toString();
            //key
            String key=typeElement.getAnnotation(BindView.class).value();
            map.put(key,className+".class");

        }
        //开始生成文件

        return false;
    }
}
