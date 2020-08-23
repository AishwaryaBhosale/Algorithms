package trees;

import java.util.*;

// A Map ADT structure using a red-black tree, where keys must implement
// Comparable.
// The key type of a map must be Comparable. Values can be any type.
public class RedBlackTreeMap<TKey extends Comparable<TKey>, TValue> {
	// A Node class.
	private class Node {
		private TKey mKey;
		private TValue mValue;
		private Node mParent;
		private Node mLeft;
		private Node mRight;
		private boolean mIsRed;

		// Constructs a new node with NIL children.
		public Node(TKey key, TValue data, boolean isRed) {
			mKey = key;
			mValue = data;
			mIsRed = isRed;

			mLeft = NIL_NODE;
			mRight = NIL_NODE;
		}

		@Override
		public String toString() {
			return mKey + " : " + mValue + " (" + (mIsRed ? "red)" : "black)");
		}
	}

	private Node mRoot;
	private int mCount;

	// Rather than create a "blank" black Node for each NIL, we use one shared
	// node for all NIL leaves.
	private final Node NIL_NODE = new Node(null, null, false);

	//////////////////// I give you these utility functions for free.

	// Get the # of keys in the tree.
	public int getCount() {
		return mCount;
	}

	// Finds the value associated with the given key.
	public TValue find(TKey key) {
		Node n = bstFind(key, mRoot); // find the Node containing the key if any
		if (n == null || n == NIL_NODE)
			throw new RuntimeException("Key not found");
		return n.mValue;
	}

	public Node root() {
		return mRoot;

	}

	/////////////////// You must finish the rest of these methods.

	// Inserts a key/value pair into the tree, updating the red/black balance
	// of nodes as necessary. Starts with a normal BST insert, then adjusts.
	public void add(TKey key, TValue data) {
		Node n = new Node(key, data, true); // nodes start red

		// normal BST insert; n will be placed into its initial position.
		// returns false if an existing node was updated (no rebalancing needed)
		boolean insertedNew = bstInsert(n, mRoot);
		if (!insertedNew)
			return;
		// check cases 1-5 for balance violations.
		checkBalance(n);
	}

	// Applies rules 1-5 to check the balance of a tree with newly inserted
	// node n.
	private void checkBalance(Node n) {
		if (n == mRoot) {
			n.mIsRed = false;
		}
		while (n.mParent != null && n.mParent.mIsRed) {
			if (n.mParent == getGrandparent(n).mLeft) {
				if (getUncle(n).mIsRed) {
					n.mParent.mIsRed = false;
					getUncle(n).mIsRed = false;
					getGrandparent(n).mIsRed = true;
					n = getGrandparent(n);
				} else {
					if (n == n.mParent.mRight) {
						n = n.mParent;
						singleRotateLeft(n);
					}
					n.mParent.mIsRed = false;
					getGrandparent(n).mIsRed = true;
					singleRotateRight(getGrandparent(n));
				}
			} else if (n.mParent == getGrandparent(n).mRight) {
				if (getUncle(n).mIsRed) {
					n.mParent.mIsRed = false;
					getUncle(n).mIsRed = false;
					getGrandparent(n).mIsRed = true;
					n = getGrandparent(n);
				} else {
					if (n == n.mParent.mLeft) {
						n = n.mParent;
						singleRotateRight(n);
					}
					n.mParent.mIsRed = false;
					getGrandparent(n).mIsRed = true;
					singleRotateLeft(getGrandparent(n));
				}
			}

		}

		mRoot.mIsRed = false;

	}


	// Returns true if the given key is in the tree.
	public boolean containsKey(TKey key) {
		// TODO: using at most three lines of code, finish this method.
		// HINT: write the bstFind method first.
		if (bstFind(key, mRoot) != null) {
			return true;
		}
		return false;
	}

	// Prints a pre-order traversal of the tree's nodes, printing the key, value,
	// and color of each node.
	public void printStructure() {
		
		// TODO: a pre-order traversal. Will need recursion.
		preOrder(mRoot);
	}
	
	private void preOrder(Node n) {
		if(n==null)
			return;
		System.out.println(n.toString());
		preOrder(n.mLeft);
		preOrder(n.mRight);
	}

	// Retuns the Node containing the given key. Recursive.
	private Node bstFind(TKey key, Node currentNode) {
		// TODO: write this method. Given a key to find and a node to start at,
		// proceed left/right from the current node until finding a node whose
		// key is equal to the given key.

		if (currentNode == NIL_NODE)
			return null;
		
		int compare = key.compareTo(currentNode.mKey);
		if (compare == 0) {
			return currentNode;
		} 
		else if(compare >0){
			return bstFind(key, currentNode.mRight);
		} 
		return bstFind(key, currentNode.mLeft); 

	}

	//////////////// These functions are needed for insertion cases.

	// Gets the grandparent of n.
	private Node getGrandparent(Node n) {
		// TODO: return the grandparent of n
		return n.mParent.mParent;
	}

	// Gets the uncle (parent's sibling) of n.
	private Node getUncle(Node n) {
		// TODO: return the uncle of n
		return n.mParent.mRight != NIL_NODE?n.mParent.mRight:n.mParent.mLeft;
	}

	// Rotate the tree right at the given node.
	private void singleRotateRight(Node n) {
		Node l = n.mLeft, lr = l.mRight, p = n.mParent;
		n.mLeft = lr;
		lr.mParent = n;
		l.mRight = n;
		n.mParent = l;

		if (p == null) { // n is root
			mRoot = l;
		} else if (p.mLeft == n) {
			p.mLeft = l;
		} else {
			p.mRight = l;
		}

		l.mParent = p;
	}

	// Rotate the tree left at the given node.
	private void singleRotateLeft(Node n) {
		// TODO: do a single left rotation (AVL tree calls this a "rr" rotation)
		// at n.
		Node r = n.mRight, rl = r.mLeft, p = n.mParent;
		n.mRight = rl;
		rl.mParent = n;
		r.mLeft = n;
		n.mParent = r;

		if (p == null) { // n is root
			mRoot = r;
		} else if (p.mRight == n) {
			p.mRight = r;
		} else {
			p.mLeft = r;
		}

		r.mParent = p;

	}

	// This method is used by insert. It is complete.
	// Inserts the key/value into the BST, and returns true if the key wasn't
	// previously in the tree.
	private boolean bstInsert(Node newNode, Node currentNode) {
		if (mRoot == null) {
			// case 1
			mRoot = newNode;
			mCount++;
			return true;
		} else {
			int compare = currentNode.mKey.compareTo(newNode.mKey);
			if (compare < 0) {
				// newNode is larger; go right.
				if (currentNode.mRight != NIL_NODE) {
					return bstInsert(newNode, currentNode.mRight);
				} else {
					currentNode.mRight = newNode;
					newNode.mParent = currentNode;
					mCount++;
					Node y = NIL_NODE;
					balanceTree(newNode, y);
					return true;
				}
			} else if (compare > 0) {
				if (currentNode.mLeft != NIL_NODE) {
					return bstInsert(newNode, currentNode.mLeft);
				} else {
					currentNode.mLeft = newNode;
					newNode.mParent = currentNode;
					mCount++;
					Node y = NIL_NODE;
					balanceTree(newNode, y);
					return true;
				}
			} else {
				// found a node with the given key; update value.
				currentNode.mValue = newNode.mValue;
				return false; // did NOT insert a new node.
			}
		}
	}

	private void balanceTree(Node n, Node uncle) {
		while (n.mParent != null && n.mParent.mIsRed) {
			if (n.mParent == n.mParent.mParent.mLeft) {
				uncle = n.mParent.mParent.mRight;
				if (uncle != NIL_NODE && uncle.mIsRed) {
					n.mParent.mIsRed = false;
					uncle.mIsRed = false;
					n.mParent.mParent.mIsRed = true;
					n = n.mParent.mParent;
					continue;
				}
				if (n == n.mParent.mRight) {
					n = n.mParent;
					singleRotateLeft(n);
				}
				n.mParent.mIsRed = false;
				n.mParent.mParent.mIsRed = true;
				singleRotateRight(n.mParent.mParent);
			} else {
				uncle = n.mParent.mParent.mLeft;
				if (uncle != NIL_NODE && uncle.mIsRed) {
					n.mParent.mIsRed = false;
					uncle.mIsRed = false;
					n.mParent.mParent.mIsRed = true;
					n = n.mParent.mParent;
					continue;
				}
				if (n == n.mParent.mLeft) {
					n = n.mParent;
					singleRotateRight(n);
				}
				n.mParent.mIsRed = false;
				n.mParent.mParent.mIsRed = true;
				singleRotateLeft(n.mParent.mParent);
			}

		}
		mRoot.mIsRed = false;

	}

	public int depthOfTree(Node root, int d) {
		if (root == null) {
			return d;
		}
		int left = d;
		int right = d;
		if (root.mLeft != null) {
			left = depthOfTree(root.mLeft, d + 1);
		}
		if (root.mRight != null) {
			right = depthOfTree(root.mRight, d + 1);
		}
		return Math.max(left, right);
	}

	public void printLevelOrder() {
		Node root=mRoot;
		int depth = depthOfTree(root, 1);
		if (root == null)
			return;

		Queue<Node> q = new LinkedList<Node>();

		q.add(root);
		while (true) {
			int nodeCount = q.size();
			if (nodeCount == 0)
				break;
			for (int i = 0; i < depth; i++) {
				System.out.print(" ");
			}
			while (nodeCount > 0) {
				Node node = q.peek();
				System.out.print("(" + node.toString() + ")");

				q.remove();

				if (node.mLeft != null)
					q.add(node.mLeft);
				if (node.mRight != null)
					q.add(node.mRight);

				if (nodeCount > 1) {
					System.out.print(", ");
				}
				nodeCount--;
			}
			depth--;
			System.out.println();
		}
	}
}
