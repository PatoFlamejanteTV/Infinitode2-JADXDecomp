package com.vladsch.flexmark.ext.yaml.front.matter;

import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/yaml/front/matter/YamlFrontMatterNode.class */
public class YamlFrontMatterNode extends Node {
    private BasedSequence key;

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.key};
    }

    public YamlFrontMatterNode(BasedSequence basedSequence, List<BasedSequence> list) {
        this.key = basedSequence;
        Iterator<BasedSequence> it = list.iterator();
        while (it.hasNext()) {
            appendChild(new YamlFrontMatterValue(it.next()));
        }
    }

    public String getKey() {
        return this.key.toString();
    }

    public BasedSequence getKeySequence() {
        return this.key;
    }

    public void setKey(BasedSequence basedSequence) {
        this.key = basedSequence;
    }

    public List<String> getValues() {
        ArrayList arrayList = new ArrayList();
        Node firstChild = getFirstChild();
        while (true) {
            Node node = firstChild;
            if (node != null) {
                arrayList.add(node.getChars().toString());
                firstChild = node.getNext();
            } else {
                return arrayList;
            }
        }
    }

    public List<BasedSequence> getValuesSequences() {
        ArrayList arrayList = new ArrayList();
        Node firstChild = getFirstChild();
        while (true) {
            Node node = firstChild;
            if (node != null) {
                arrayList.add(node.getChars());
                firstChild = node.getNext();
            } else {
                return arrayList;
            }
        }
    }
}
