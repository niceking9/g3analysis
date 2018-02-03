package cn.yobir.bizimp;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.yobir.g3.biz.NumbService;
import cn.yobir.g3.model.Numb;

public class XMLmsgListen implements ServletContextListener {
	private static NumbService nbs = new NumbServiceimp();

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		URL url = null;
		try {
			url = new URL("http://f.apiplus.net/fc3d-20.xml");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		List<Numb> numbs = null;
		try {
			numbs = getXML(url);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		for (ListIterator ltr = numbs.listIterator(); ltr.hasNext();) {
			try {
				nbs.addNumb((Numb)ltr.next());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public List<Numb> getXML(URL url) throws DocumentException {
		List<Numb> numbs = new ArrayList();
		SAXReader reader = new SAXReader();
		Document document = reader.read(url);
		Element root = document.getRootElement();
		for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
			Element element = it.next();
			Numb numb = new Numb();
			for (Iterator<Attribute> it1 = element.attributeIterator(); it1.hasNext();) {
				Attribute attribute = it1.next();

				if (attribute.getName().equals("expect")) {
					numb.setMark(attribute.getValue());
				} else if (attribute.getName().equals("opencode")) {
					numb.setNum(attribute.getValue());
				} else {
					numb.setDate(attribute.getValue());
				}
			}
			numbs.add(numb);
		}

		return numbs;
	}
}
