package part1.lesson8;

import part1.lesson02.task03.Person;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class SerializeWithJAXB {

    public static void main(String[] args) throws JAXBException {
        Person person = new Person(20, Person.Sex.MAN, "Tom");
        StringWriter writer = new StringWriter();

        //создание объекта Marshaller, который выполняет сериализацию
        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        // сама сериализация
        marshaller.marshal(person, writer);

        //преобразовываем в строку все записанное в StringWriter
        String result = writer.toString();
        System.out.println(result);

        StringReader reader = new StringReader(result);

        JAXBContext context1 = JAXBContext.newInstance(Person.class);
        Unmarshaller unmarshaller = context1.createUnmarshaller();

        Person person1 = (Person) unmarshaller.unmarshal(reader);
        System.out.println(person1);
    }
}
