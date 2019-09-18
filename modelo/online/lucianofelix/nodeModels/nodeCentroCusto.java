package online.lucianofelix.nodeModels;

import java.util.Enumeration;

import javax.swing.tree.TreeNode;

import online.lucianofelix.beans.CentroCusto;

public class nodeCentroCusto implements TreeNode {
	CentroCusto cCusto;
	public nodeCentroCusto(CentroCusto cCusto) {
		this.cCusto = cCusto;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getChildCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIndex(TreeNode node) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Enumeration children() {
		// TODO Auto-generated method stub
		return null;
	}

}
