package org.ares.app.common.xml.spring;

import java.io.IOException;
import java.io.Writer;

import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.transform.Source;

import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;

import com.sun.xml.bind.marshaller.CharacterEscapeHandler;

public class UserJaxb2RootElementHttpMessageConverter extends Jaxb2RootElementHttpMessageConverter {

	@Override
	protected Object readFromSource(Class<? extends Object> clazz, HttpHeaders headers, Source source)
			throws IOException {
		return super.readFromSource(clazz, headers, source);
	}

	protected void customizeMarshaller(Marshaller marshaller) {
		try {
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);// 去掉生成xml的默认报文头
			marshaller.setProperty(CharacterEscapeHandler.class.getName(),
					new CharacterEscapeHandler() {
						@Override
						public void escape(char[] ch, int start, int length, boolean isAttVal, Writer writer)
								throws IOException {
							writer.write(ch, start, length);
						}
					});
		} catch (PropertyException e) {
			e.printStackTrace();
		}
	}

}
