package org.jsoup.helper;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Stack;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathFactoryConfigurationException;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.Parser;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;
import org.jsoup.select.Selector;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* loaded from: infinitode-2.jar:org/jsoup/helper/W3CDom.class */
public class W3CDom {
    public static final String SourceProperty = "jsoupSource";
    private static final String ContextProperty = "jsoupContextSource";
    private static final String ContextNodeProperty = "jsoupContextNode";
    public static final String XPathFactoryProperty = "javax.xml.xpath.XPathFactory:jsoup";
    private boolean namespaceAware = true;
    protected DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    public W3CDom() {
        this.factory.setNamespaceAware(true);
    }

    public boolean namespaceAware() {
        return this.namespaceAware;
    }

    public W3CDom namespaceAware(boolean z) {
        this.namespaceAware = z;
        this.factory.setNamespaceAware(z);
        return this;
    }

    public static Document convert(org.jsoup.nodes.Document document) {
        return new W3CDom().fromJsoup(document);
    }

    public static String asString(Document document, Map<String, String> map) {
        try {
            DOMSource dOMSource = new DOMSource(document);
            StringWriter stringWriter = new StringWriter();
            StreamResult streamResult = new StreamResult(stringWriter);
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            if (map != null) {
                newTransformer.setOutputProperties(propertiesFromMap(map));
            }
            if (document.getDoctype() != null) {
                DocumentType doctype = document.getDoctype();
                if (!StringUtil.isBlank(doctype.getPublicId())) {
                    newTransformer.setOutputProperty("doctype-public", doctype.getPublicId());
                }
                if (!StringUtil.isBlank(doctype.getSystemId())) {
                    newTransformer.setOutputProperty("doctype-system", doctype.getSystemId());
                } else if (doctype.getName().equalsIgnoreCase("html") && StringUtil.isBlank(doctype.getPublicId()) && StringUtil.isBlank(doctype.getSystemId())) {
                    newTransformer.setOutputProperty("doctype-system", "about:legacy-compat");
                }
            }
            newTransformer.transform(dOMSource, streamResult);
            return stringWriter.toString();
        } catch (TransformerException e) {
            throw new IllegalStateException(e);
        }
    }

    static Properties propertiesFromMap(Map<String, String> map) {
        Properties properties = new Properties();
        properties.putAll(map);
        return properties;
    }

    public static HashMap<String, String> OutputHtml() {
        return methodMap("html");
    }

    public static HashMap<String, String> OutputXml() {
        return methodMap("xml");
    }

    private static HashMap<String, String> methodMap(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("method", str);
        return hashMap;
    }

    public Document fromJsoup(org.jsoup.nodes.Document document) {
        return fromJsoup((Element) document);
    }

    public Document fromJsoup(Element element) {
        Validate.notNull(element);
        try {
            DocumentBuilder newDocumentBuilder = this.factory.newDocumentBuilder();
            DOMImplementation dOMImplementation = newDocumentBuilder.getDOMImplementation();
            Document newDocument = newDocumentBuilder.newDocument();
            org.jsoup.nodes.Document ownerDocument = element.ownerDocument();
            org.jsoup.nodes.DocumentType documentType = ownerDocument != null ? ownerDocument.documentType() : null;
            org.jsoup.nodes.DocumentType documentType2 = documentType;
            if (documentType != null) {
                try {
                    newDocument.appendChild(dOMImplementation.createDocumentType(documentType2.name(), documentType2.publicId(), documentType2.systemId()));
                } catch (DOMException unused) {
                }
            }
            newDocument.setXmlStandalone(true);
            newDocument.setUserData(ContextProperty, element instanceof org.jsoup.nodes.Document ? element.firstElementChild() : element, null);
            convert(ownerDocument != null ? ownerDocument : element, newDocument);
            return newDocument;
        } catch (ParserConfigurationException e) {
            throw new IllegalStateException(e);
        }
    }

    public void convert(org.jsoup.nodes.Document document, Document document2) {
        convert((Element) document, document2);
    }

    public void convert(Element element, Document document) {
        W3CBuilder w3CBuilder = new W3CBuilder(document);
        w3CBuilder.namespaceAware = this.namespaceAware;
        org.jsoup.nodes.Document ownerDocument = element.ownerDocument();
        if (ownerDocument != null) {
            if (!StringUtil.isBlank(ownerDocument.location())) {
                document.setDocumentURI(ownerDocument.location());
            }
            w3CBuilder.syntax = ownerDocument.outputSettings().syntax();
        }
        NodeTraversor.traverse(w3CBuilder, element instanceof org.jsoup.nodes.Document ? element.firstElementChild() : element);
    }

    public NodeList selectXpath(String str, Document document) {
        return selectXpath(str, (Node) document);
    }

    public NodeList selectXpath(String str, Node node) {
        XPathFactory newInstance;
        Validate.notEmptyParam(str, "xpath");
        Validate.notNullParam(node, "contextNode");
        try {
            if (System.getProperty(XPathFactoryProperty) != null) {
                newInstance = XPathFactory.newInstance("jsoup");
            } else {
                newInstance = XPathFactory.newInstance();
            }
            NodeList nodeList = (NodeList) newInstance.newXPath().compile(str).evaluate(node, XPathConstants.NODESET);
            Validate.notNull(nodeList);
            return nodeList;
        } catch (XPathExpressionException | XPathFactoryConfigurationException e) {
            throw new Selector.SelectorParseException(e, "Could not evaluate XPath query [%s]: %s", str, e.getMessage());
        }
    }

    public <T extends org.jsoup.nodes.Node> List<T> sourceNodes(NodeList nodeList, Class<T> cls) {
        Validate.notNull(nodeList);
        Validate.notNull(cls);
        ArrayList arrayList = new ArrayList(nodeList.getLength());
        for (int i = 0; i < nodeList.getLength(); i++) {
            Object userData = nodeList.item(i).getUserData(SourceProperty);
            if (cls.isInstance(userData)) {
                arrayList.add(cls.cast(userData));
            }
        }
        return arrayList;
    }

    public Node contextNode(Document document) {
        return (Node) document.getUserData(ContextNodeProperty);
    }

    public String asString(Document document) {
        return asString(document, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:org/jsoup/helper/W3CDom$W3CBuilder.class */
    public static class W3CBuilder implements NodeVisitor {
        private static final String xmlnsKey = "xmlns";
        private static final String xmlnsPrefix = "xmlns:";
        private final Document doc;
        private Node dest;
        private final Element contextElement;
        private boolean namespaceAware = true;
        private final Stack<HashMap<String, String>> namespacesStack = new Stack<>();
        private Document.OutputSettings.Syntax syntax = Document.OutputSettings.Syntax.xml;

        public W3CBuilder(org.w3c.dom.Document document) {
            this.doc = document;
            this.namespacesStack.push(new HashMap<>());
            this.dest = document;
            this.contextElement = (Element) document.getUserData(W3CDom.ContextProperty);
            org.jsoup.nodes.Document ownerDocument = this.contextElement.ownerDocument();
            if (this.namespaceAware && ownerDocument != null && (ownerDocument.parser().getTreeBuilder() instanceof HtmlTreeBuilder)) {
                this.namespacesStack.peek().put("", Parser.NamespaceHtml);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0081 A[Catch: DOMException -> 0x0097, TryCatch #0 {DOMException -> 0x0097, blocks: (B:20:0x0051, B:10:0x0060, B:12:0x0081, B:13:0x008f), top: B:19:0x0051 }] */
        @Override // org.jsoup.select.NodeVisitor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void head(org.jsoup.nodes.Node r7, int r8) {
            /*
                Method dump skipped, instructions count: 287
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.helper.W3CDom.W3CBuilder.head(org.jsoup.nodes.Node, int):void");
        }

        private void append(Node node, org.jsoup.nodes.Node node2) {
            node.setUserData(W3CDom.SourceProperty, node2, null);
            this.dest.appendChild(node);
        }

        @Override // org.jsoup.select.NodeVisitor
        public void tail(org.jsoup.nodes.Node node, int i) {
            if ((node instanceof Element) && (this.dest.getParentNode() instanceof org.w3c.dom.Element)) {
                this.dest = this.dest.getParentNode();
            }
            this.namespacesStack.pop();
        }

        private void copyAttributes(org.jsoup.nodes.Node node, org.w3c.dom.Element element) {
            Iterator<Attribute> it = node.attributes().iterator();
            while (it.hasNext()) {
                Attribute next = it.next();
                String validKey = Attribute.getValidKey(next.getKey(), this.syntax);
                if (validKey != null) {
                    element.setAttribute(validKey, next.getValue());
                }
            }
        }

        private String updateNamespaces(Element element) {
            String str;
            Iterator<Attribute> it = element.attributes().iterator();
            while (it.hasNext()) {
                Attribute next = it.next();
                String key = next.getKey();
                if (key.equals(xmlnsKey)) {
                    str = "";
                } else if (key.startsWith(xmlnsPrefix)) {
                    str = key.substring(6);
                }
                this.namespacesStack.peek().put(str, next.getValue());
            }
            int indexOf = element.tagName().indexOf(58);
            return indexOf > 0 ? element.tagName().substring(0, indexOf) : "";
        }
    }
}
