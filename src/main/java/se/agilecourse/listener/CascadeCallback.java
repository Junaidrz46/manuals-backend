package se.agilecourse.listener;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.util.ReflectionUtils;
import se.agilecourse.annotation.CascadeSave;

import java.lang.reflect.Field;

public class CascadeCallback implements ReflectionUtils.FieldCallback {
    private Object source;
    private  MongoOperations mongoOperations;
    public CascadeCallback(Object source, MongoOperations mongoOperations) {
        this.source=source;
        this.mongoOperations=mongoOperations;
    }
    public CascadeCallback(){}
    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public MongoOperations getMongoOperations() {
        return mongoOperations;
    }

    public void setMongoOperations(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
        ReflectionUtils.makeAccessible(field);

        if (field.isAnnotationPresent(DBRef.class) &&
                field.isAnnotationPresent(CascadeSave.class)) {

            Object fieldValue = field.get(getSource());
            if (fieldValue != null) {
                CascadeCallback callback = new CascadeCallback();
                ReflectionUtils.doWithFields(fieldValue.getClass(), callback);

                getMongoOperations().save(fieldValue);
            }
        }
    }
}
