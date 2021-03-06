/*******************************************************************************
 * This file is part of the EEG-database project
 * 
 *   ==========================================
 *  
 *   Copyright (C) 2013 by University of West Bohemia (http://www.zcu.cz/en/)
 *  
 *  ***********************************************************************************************************************
 *  
 *   Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 *   the License. You may obtain a copy of the License at
 *  
 *       http://www.apache.org/licenses/LICENSE-2.0
 *  
 *   Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 *   an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 *   specific language governing permissions and limitations under the License.
 *  
 *  ***********************************************************************************************************************
 *  
 *   ODMLSectionDeserializer.java, 2015/02/26 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.wui.core.experiments.metadata;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import odml.core.Reader;
import odml.core.Section;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.ajax.json.JSONObject;
import org.apache.wicket.ajax.json.XML;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

public class ODMLSectionDeserializer extends JsonDeserializer<Section> {

    protected Log log = LogFactory.getLog(getClass());

    @Override
    public Section deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        long start = System.currentTimeMillis();

        try {

            String jsonString = jp.getCodec().readTree(jp).toString();
            JSONObject jsonObject = new JSONObject(jsonString);
            String xmlString = XML.toString(jsonObject);
            Reader reader = new Reader();

            ByteArrayInputStream stream = new ByteArrayInputStream(xmlString.getBytes("UTF-8")); // encoding is necessary

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbuilder = dbf.newDocumentBuilder();
            InputSource source = new InputSource(stream);
            source.setEncoding("UTF-8"); // encoding is necessary
            Document dom = dbuilder.parse(source);
            Element rootElement = dom.getDocumentElement();
            rootElement.setAttribute("version", "1.0");

            byte[] xmlStringUpdated = getStringFromDocument(dom).getBytes("UTF-8");
            stream = new ByteArrayInputStream(xmlStringUpdated); // encoding is necessary
            Section load = reader.load(stream);
            return load;
        } catch (JSONException e) {
            log.warn(e.getMessage(), e);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        } finally {
            long end = System.currentTimeMillis();
            log.trace("Deserialize time - " + (end - start) + " ms.");
        }
        return null;
    }

    public String getStringFromDocument(Document doc) {
        try {
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            return writer.toString();
        } catch (TransformerException e) {
            log.warn(e.getMessage(), e);
            return "";
        }
    }
}
