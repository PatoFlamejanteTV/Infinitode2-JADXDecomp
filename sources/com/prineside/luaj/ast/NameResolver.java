package com.prineside.luaj.ast;

import com.prineside.luaj.LuaValue;
import com.prineside.luaj.ast.Exp;
import com.prineside.luaj.ast.Stat;
import java.util.List;
import net.bytebuddy.description.method.ParameterDescription;

/* loaded from: infinitode-2.jar:com/prineside/luaj/ast/NameResolver.class */
public class NameResolver extends Visitor {

    /* renamed from: a, reason: collision with root package name */
    private NameScope f1524a = null;

    private void a() {
        this.f1524a = new NameScope(this.f1524a);
    }

    private void b() {
        this.f1524a = this.f1524a.outerScope;
    }

    @Override // com.prineside.luaj.ast.Visitor
    public void visit(NameScope nameScope) {
    }

    @Override // com.prineside.luaj.ast.Visitor
    public void visit(Block block) {
        a();
        block.scope = this.f1524a;
        super.visit(block);
        b();
    }

    @Override // com.prineside.luaj.ast.Visitor
    public void visit(FuncBody funcBody) {
        a();
        this.f1524a.functionNestingCount++;
        funcBody.scope = this.f1524a;
        super.visit(funcBody);
        b();
    }

    @Override // com.prineside.luaj.ast.Visitor
    public void visit(Stat.LocalFuncDef localFuncDef) {
        a(localFuncDef.name);
        super.visit(localFuncDef);
    }

    @Override // com.prineside.luaj.ast.Visitor
    public void visit(Stat.NumericFor numericFor) {
        a();
        numericFor.scope = this.f1524a;
        a(numericFor.name);
        super.visit(numericFor);
        b();
    }

    @Override // com.prineside.luaj.ast.Visitor
    public void visit(Stat.GenericFor genericFor) {
        a();
        genericFor.scope = this.f1524a;
        a(genericFor.names);
        super.visit(genericFor);
        b();
    }

    @Override // com.prineside.luaj.ast.Visitor
    public void visit(Exp.NameExp nameExp) {
        nameExp.name.variable = b(nameExp.name);
        super.visit(nameExp);
    }

    @Override // com.prineside.luaj.ast.Visitor
    public void visit(Stat.FuncDef funcDef) {
        funcDef.name.name.variable = b(funcDef.name.name);
        funcDef.name.name.variable.hasassignments = true;
        super.visit(funcDef);
    }

    @Override // com.prineside.luaj.ast.Visitor
    public void visit(Stat.Assign assign) {
        super.visit(assign);
        int size = assign.vars.size();
        for (int i = 0; i < size; i++) {
            assign.vars.get(i).markHasAssignment();
        }
    }

    @Override // com.prineside.luaj.ast.Visitor
    public void visit(Stat.LocalAssign localAssign) {
        visitExps(localAssign.values);
        a(localAssign.names);
        int size = localAssign.names.size();
        int size2 = localAssign.values != null ? localAssign.values.size() : 0;
        int i = size2;
        boolean z = size2 > 0 && i < size && localAssign.values.get(i - 1).isvarargexp();
        for (int i2 = 0; i2 < size; i2++) {
            if (i2 >= (z ? i - 1 : i)) {
                break;
            }
            if (localAssign.values.get(i2) instanceof Exp.Constant) {
                localAssign.names.get(i2).variable.initialValue = ((Exp.Constant) localAssign.values.get(i2)).value;
            }
        }
        if (!z) {
            for (int i3 = i; i3 < size; i3++) {
                localAssign.names.get(i3).variable.initialValue = LuaValue.NIL;
            }
        }
    }

    @Override // com.prineside.luaj.ast.Visitor
    public void visit(ParList parList) {
        if (parList.names != null) {
            a(parList.names);
        }
        if (parList.isvararg) {
            this.f1524a.define(ParameterDescription.NAME_PREFIX);
        }
        super.visit(parList);
    }

    private void a(List<Name> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            a(list.get(i));
        }
    }

    private void a(Name name) {
        name.variable = this.f1524a.define(name.name);
    }

    private Variable b(Name name) {
        Variable find = this.f1524a.find(name.name);
        if (find.isLocal() && this.f1524a.functionNestingCount != find.definingScope.functionNestingCount) {
            find.isupvalue = true;
        }
        return find;
    }
}
