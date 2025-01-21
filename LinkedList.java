/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		//// Replace the following statement with your code
		Node p = this.first;

		for (int i = 0; i < size ;i++ ) {
			if (i == index) {
				return p;
			}
			p = p.next;
		}

		return null;
	}
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		//// Write your code here
		Node p = this.first;
		Node node = new Node(block);

		for (int i = 0; i < index-1 ; i++ ) {
				p.next = node.next;
			}
			node.next = p.next;
			p.next = node
		}
		size++;
	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		//// Write your code here
		Node node = new Node(block);
	    if (size == 0) {
	        first = last; 
	        last = node;
	    } else {
	        last.next = node;
	        last = node;
	    }

	    size++;
	}
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		//// Write your code here
		Node node = new Node(block);
		first = node;

		if ( index == 0){
			last = node;
		}
			size++;

		}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		//// Replace the following statement with your code
			if (index < 0 || index > size-1) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		Node p = this.first;
		for (int i = 0 ; i < index ; i++) {
			p = p.next;
		}

		return p.block;
	}	

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		//// Replace the following statement with your code
		Node p = this.first;
		int indexBlock = 0;

		while (p != null) {
			if (p.block == block) {
				return indexBlock;
			}
			p = p.next;
			indexBlock++;
		}
		return -1;
	}

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		//// Write your code here

		if (p == null) {
			return;
		}

	    if (this.first == node) {
	        this.first = this.first.next;
	        size--;
	        return;
	    }
		Node p = this.first;
		while( p.next != null) {
			if (p.next == null) {
				p.next = p.next.next;
				size--;
				return;
			}
			p = p.next;
		}
	}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		//// Write your code here
		if (index < 0 || index > size-1) {
		throw new IllegalArgumentException(
			"index must be between 0 and size");
		}

	    if (index == 0) {
	        this.first = this.first.next;
	        size--;
	        return;
	    }

	    Node p = this.first;
	    for (int i = 0; i < index - 1; i++) {
	        p = p.next;
	    }

	    p.next = p.next.next;
	    size--;
	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		//// Write your code here
		if (this.first == null) {
			throw new IllegalArgumentException("The given block is not in the list.");
		}
		Node p = this.first;

	    if (this.first.block.equals(block)) {
	        this.first = this.first.next;
	        size--;
	        return;
	    }

	    while (p.next != null) {
	        if (p.next.block.equals(block)) {
	            p.next = p.next.next;
	            size--;
	            return;
	        }
	        p = p.next;
	    }

	    throw new IllegalArgumentException("The given block is not in the list.");
	}


	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
	    if (this.first == null) {
	        return "[]";
	    }

	    Node p = this.first;
	    StringBuilder result = new StringBuilder();
	    result.append("[");
	    
	    while (p != null) {
	        result.append(p.block.toString());
	        if (p.next != null) {
	            result.append(", ");
	        }
	        p = p.next;
	    }
	    
	    result.append("]");
	    return result.toString();
	}
