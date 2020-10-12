package ru.geniusjohn.phonebook.xml.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.geniusjohn.phonebook.domain.Group;
import ru.geniusjohn.phonebook.repositories.GroupRepository;
import ru.geniusjohn.phonebook.xml.element.YealinkMenu;
import ru.geniusjohn.phonebook.xml.element.YealinkMenuItem;
import ru.geniusjohn.phonebook.xml.element.YealinkSoftKeyItem;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.OutputStream;
import java.util.ArrayList;

@Component
public class XmlYealinkMenuGenerator implements XmlMenuGenerator {

    private GroupRepository groupRepository;
    private String baseUrl;

    @Value("${ru.omc.webphonebook.base-url}")
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }


    @Autowired
    public void setGroupRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public void generate(OutputStream outputStream) throws JAXBException {
        YealinkMenu yealinkMenu = new YealinkMenu();
        yealinkMenu.setMenuItems(new ArrayList<>());
        yealinkMenu.setSoftKeyItems(new ArrayList<>());
        for (Group group: groupRepository.findByOrderByOrderGroup()) {
            String url = String.format("%s/phonebook/getGroupXml/%d", baseUrl, group.getId());
            yealinkMenu.getMenuItems().add(new YealinkMenuItem(group, url));
            yealinkMenu.getSoftKeyItems().add(new YealinkSoftKeyItem(group, url));
        }
        JAXBContext context = JAXBContext.newInstance(YealinkMenu.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        mar.marshal(yealinkMenu, outputStream);
    }

}
