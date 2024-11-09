package com.prineside.luaj.ast;

/* loaded from: infinitode-2.jar:com/prineside/luaj/ast/Chunk.class */
public class Chunk extends SyntaxElement {
    public final Block block;

    public Chunk(Block block) {
        this.block = block;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
