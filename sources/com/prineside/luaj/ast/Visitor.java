package com.prineside.luaj.ast;

import com.prineside.luaj.ast.Exp;
import com.prineside.luaj.ast.Stat;
import java.util.List;

/* loaded from: infinitode-2.jar:com/prineside/luaj/ast/Visitor.class */
public abstract class Visitor {
    public void visit(Chunk chunk) {
        chunk.block.accept(this);
    }

    public void visit(Block block) {
        visit(block.scope);
        if (block.stats != null) {
            int size = block.stats.size();
            for (int i = 0; i < size; i++) {
                block.stats.get(i).accept(this);
            }
        }
    }

    public void visit(Stat.Assign assign) {
        visitVars(assign.vars);
        visitExps(assign.exps);
    }

    public void visit(Stat.Break r2) {
    }

    public void visit(Stat.FuncCallStat funcCallStat) {
        funcCallStat.funccall.accept(this);
    }

    public void visit(Stat.FuncDef funcDef) {
        funcDef.body.accept(this);
    }

    public void visit(Stat.GenericFor genericFor) {
        visit(genericFor.scope);
        visitNames(genericFor.names);
        visitExps(genericFor.exps);
        genericFor.block.accept(this);
    }

    public void visit(Stat.IfThenElse ifThenElse) {
        ifThenElse.ifexp.accept(this);
        ifThenElse.ifblock.accept(this);
        if (ifThenElse.elseifblocks != null) {
            int size = ifThenElse.elseifblocks.size();
            for (int i = 0; i < size; i++) {
                ifThenElse.elseifexps.get(i).accept(this);
                ifThenElse.elseifblocks.get(i).accept(this);
            }
        }
        if (ifThenElse.elseblock != null) {
            visit(ifThenElse.elseblock);
        }
    }

    public void visit(Stat.LocalAssign localAssign) {
        visitNames(localAssign.names);
        visitExps(localAssign.values);
    }

    public void visit(Stat.LocalFuncDef localFuncDef) {
        visit(localFuncDef.name);
        localFuncDef.body.accept(this);
    }

    public void visit(Stat.NumericFor numericFor) {
        visit(numericFor.scope);
        visit(numericFor.name);
        numericFor.initial.accept(this);
        numericFor.limit.accept(this);
        if (numericFor.step != null) {
            numericFor.step.accept(this);
        }
        numericFor.block.accept(this);
    }

    public void visit(Stat.RepeatUntil repeatUntil) {
        repeatUntil.block.accept(this);
        repeatUntil.exp.accept(this);
    }

    public void visit(Stat.Return r4) {
        visitExps(r4.values);
    }

    public void visit(Stat.WhileDo whileDo) {
        whileDo.exp.accept(this);
        whileDo.block.accept(this);
    }

    public void visit(FuncBody funcBody) {
        visit(funcBody.scope);
        funcBody.parlist.accept(this);
        funcBody.block.accept(this);
    }

    public void visit(FuncArgs funcArgs) {
        visitExps(funcArgs.exps);
    }

    public void visit(TableField tableField) {
        if (tableField.name != null) {
            visit(tableField.name);
        }
        if (tableField.index != null) {
            tableField.index.accept(this);
        }
        tableField.rhs.accept(this);
    }

    public void visit(Exp.AnonFuncDef anonFuncDef) {
        anonFuncDef.body.accept(this);
    }

    public void visit(Exp.BinopExp binopExp) {
        binopExp.lhs.accept(this);
        binopExp.rhs.accept(this);
    }

    public void visit(Exp.Constant constant) {
    }

    public void visit(Exp.FieldExp fieldExp) {
        fieldExp.lhs.accept(this);
        visit(fieldExp.name);
    }

    public void visit(Exp.FuncCall funcCall) {
        funcCall.lhs.accept(this);
        funcCall.args.accept(this);
    }

    public void visit(Exp.IndexExp indexExp) {
        indexExp.lhs.accept(this);
        indexExp.exp.accept(this);
    }

    public void visit(Exp.MethodCall methodCall) {
        methodCall.lhs.accept(this);
        visit(methodCall.name);
        methodCall.args.accept(this);
    }

    public void visit(Exp.NameExp nameExp) {
        visit(nameExp.name);
    }

    public void visit(Exp.ParensExp parensExp) {
        parensExp.exp.accept(this);
    }

    public void visit(Exp.UnopExp unopExp) {
        unopExp.rhs.accept(this);
    }

    public void visit(Exp.VarargsExp varargsExp) {
    }

    public void visit(ParList parList) {
        visitNames(parList.names);
    }

    public void visit(TableConstructor tableConstructor) {
        if (tableConstructor.fields != null) {
            int size = tableConstructor.fields.size();
            for (int i = 0; i < size; i++) {
                tableConstructor.fields.get(i).accept(this);
            }
        }
    }

    public void visitVars(List<Exp.VarExp> list) {
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                list.get(i).accept(this);
            }
        }
    }

    public void visitExps(List<Exp> list) {
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                list.get(i).accept(this);
            }
        }
    }

    public void visitNames(List<Name> list) {
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                visit(list.get(i));
            }
        }
    }

    public void visit(Name name) {
    }

    public void visit(String str) {
    }

    public void visit(NameScope nameScope) {
    }

    public void visit(Stat.Goto r2) {
    }

    public void visit(Stat.Label label) {
    }
}
