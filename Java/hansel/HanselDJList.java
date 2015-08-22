/** Copyright (C) 2015 R. Scott H/\ker, with "/\" replaced by "ac". 
This file is part of the DeducerHansel package by the above copyright holder,
and is covered by the GNU General Public License version 2 (GPLv2).
See the README.md and LICENSE files in the main DeducerHansel folder for this package
for copyright and warranty details. The GPLv2 license is also is also
available at http://www.gnu.org/licenses/gpl-2.0.html, or from the
Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
 
 DeducerHansel java files have liberally borrowed code from many java files from the following packages by Ian Fellows:
     Deducer (0.7-6 version, dated 2012-12-05)
     DeducerPlugInExample (0.2-0 version, dated 2012-03-16)
The code in the files of the above packages is covered by the GPLv2 licenses for those packages.
 
 The current file notably uses a substantial amount of code from HanselDJList.java from the Deducer package.

The current file made adjustments to that earlier java code on 2013-04-11 to work with the DeducerHansel package.
 Subsequent modification dates: 2015-03-13, 2015-08-06.
 */

package hansel;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;


import java.io.IOException;
import java.util.*;



public class HanselDJList extends JList implements DragSourceListener, DropTargetListener, DragGestureListener{
	protected ArrayListTransferHandler arrayListHandler;
    protected int dragIndex=-1;
    protected boolean isAtEnd=false;
    protected int dragMode = TransferHandler.COPY_OR_MOVE;
	Object dropTargetCell;
	
	public HanselDJList(){
		super();
		arrayListHandler= new ArrayListTransferHandler();
		this.setTransferHandler(arrayListHandler);
		this.setDragEnabled(true);
		this.setCellRenderer(new DListCellRenderer());
		DragSource dragSource = new DragSource();
        DragGestureRecognizer dgr =
            dragSource.createDefaultDragGestureRecognizer (this,
                                                           DnDConstants.ACTION_MOVE,
                                                           this);
        DropTarget dropTarget = new DropTarget (this, this);
        
		this.setModel(new DefaultListModel());
	}
	
	public int getTransferMode(){
		return dragMode;
	}
	
	public void setTransferMode(int mode){
		dragMode = mode;
		arrayListHandler= new ArrayListTransferHandler();
		arrayListHandler.setTransferMode(mode);
		this.setTransferHandler(arrayListHandler);
	}
	
	
	
	
	

	// custom renderer
    class DListCellRenderer
        extends DefaultListCellRenderer {
        boolean isLastItem;
        int thisIndex;
        Insets normalInsets, lastItemInsets;
        int BOTTOM_PAD = 30;
        public DListCellRenderer() {
            super();
            normalInsets = super.getInsets();
            lastItemInsets =
                new Insets (normalInsets.top,
                            normalInsets.left,
                            normalInsets.bottom + BOTTOM_PAD,
                            normalInsets.right);
        }
        public Component getListCellRendererComponent (JList list,
                                                       Object value,
                                                       int index,
                                                       boolean isSelected,
                                                       boolean hasFocus) {
            thisIndex = index;
            isLastItem = (index == list.getModel().getSize()-1);
            return super.getListCellRendererComponent (list, value,
                                                       index, isSelected,
                                                       hasFocus);
        }
        public void paintComponent (Graphics g) {
            super.paintComponent(g);          
            if(thisIndex==dragIndex && !isAtEnd) {
                g.setColor(Color.black);
                g.drawLine (0, 0, getSize().width, 0);
            }else if(isAtEnd && isLastItem){
                g.setColor(Color.black);
                g.drawLine (0, getSize().height-1, getSize().width, getSize().height-1);            	
            }
        }
    }
    
	public void dragDropEnd(DragSourceDropEvent arg0) { 
		dragIndex=-1;
		isAtEnd=false;
		repaint();
	}
	public void dragEnter(DragSourceDragEvent dtde) {}
	public void dragExit(DragSourceEvent arg0) {}
	public void dragOver(DragSourceDragEvent arg0) {}
	public void dropActionChanged(DragSourceDragEvent arg0) {}
	public void dragEnter(DropTargetDragEvent dtde) {
		dtde.acceptDrag(DnDConstants.ACTION_COPY_OR_MOVE);
	}
	public void dragExit(DropTargetEvent arg0) {
		dragIndex=-1;
		isAtEnd=false;
		repaint();
	}
    public void dragOver (DropTargetDragEvent dtde) {
        Point dragPoint = dtde.getLocation();
        dragIndex = locationToIndex (dragPoint);
        Rectangle listRect = this.getCellBounds(0, this.getModel().getSize()-1);
        isAtEnd =!(listRect==null || listRect.contains(dragPoint)) ;
        if(!isAtEnd)
        	this.setSelectedIndex(dragIndex);
        else
        	this.getSelectionModel().clearSelection();
        repaint();
    }
	public void drop(DropTargetDropEvent dtde) {
		try{		
			dtde.acceptDrop(DnDConstants.ACTION_MOVE);
			
			boolean dropped = arrayListHandler.importData(this,dtde.getTransferable());
			int[] selected = this.getSelectedIndices();
			if(selected.length>0)
				this.getSelectionModel().removeSelectionInterval(
						selected[selected.length-1],selected[selected.length-1]);	
			
			dtde.dropComplete(dropped);
			dragIndex=-1;
			isAtEnd=false;

			repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	public void dropActionChanged(DropTargetDragEvent arg0) {}
	public void dragGestureRecognized(DragGestureEvent arg0) {}

}
class ArrayListTransferHandler extends TransferHandler {


	DataFlavor localArrayListFlavor, serialArrayListFlavor;

	  String localArrayListType = DataFlavor.javaJVMLocalObjectMimeType
	      + ";class=java.util.ArrayList";

	  static JList source = null;
	  static JList target = null;

	  int[] indices = null;

	  int addIndex = -1; //Location where items were added

	  int addCount = 0; //Number of items added
	  
	  int dragMode = TransferHandler.COPY_OR_MOVE;

	  public ArrayListTransferHandler() {
	    try {
	      localArrayListFlavor = new DataFlavor(localArrayListType);
	    } catch (ClassNotFoundException e) {
	     // System.out
	      //    .println("ArrayListTransferHandler: unable to create data flavor");
	    }
	    serialArrayListFlavor = new DataFlavor(ArrayList.class, "ArrayList");
	  }
	  
	  public void setTransferMode(int mode){
		  dragMode = mode;
	  }
	  
	  public int getTransferMode(){return dragMode;}

	  public boolean importData(JComponent c, Transferable t) {
	    target = null;
	    ArrayList alist = null;
	    if (!canImport(c, t.getTransferDataFlavors())) {
	      return false;
	    }
	    try {
	      target = (JList) c;
	      if (hasLocalArrayListFlavor(t.getTransferDataFlavors())) {
	        alist = (ArrayList) t.getTransferData(localArrayListFlavor);
	      } else if (hasSerialArrayListFlavor(t.getTransferDataFlavors())) {
	        alist = (ArrayList) t.getTransferData(serialArrayListFlavor);
	      } else {
	        return false;
	      }
	    } catch (UnsupportedFlavorException ufe) {
	     // System.out.println("importData: unsupported data flavor");
	      return false;
	    } catch (IOException ioe) {
	    //  System.out.println("importData: I/O exception");
	      return false;
	    }

	    //At this point we use the same code to retrieve the data
	    //locally or serially.

	    //We'll drop at the current selected index.
	    int index = target.getSelectedIndex();
	    //System.out.println(index);
	    DefaultListModel listModel = (DefaultListModel) target.getModel();
	    int max = listModel.getSize();
	    if (index < 0) {
	      index = max;
	    } else if (index > max) {
	        index = max;
	    }		    
	    //Prevent the user from dropping data back on itself.
	    //For example, if the user is moving items #4,#5,#6 and #7 and
	    //attempts to insert the items after item #5, this would
	    //be problematic when removing the original items.
	    //This is interpreted as dropping the same data on itself
	    //and has no effect.
	    if (source!=null && source.equals(target)) {
	      if (indices != null && index >= indices[0] - 1
	          && index <= indices[indices.length - 1]) {
	        indices = null;
	        return true;
	      }
	    }


	    
	    addIndex = index;
	    addCount = alist.size();
	    for (int i = 0; i < alist.size(); i++) {
	      listModel.add(index++, alist.get(i));
	    }
	    return true;
	  }

	  protected void exportDone(JComponent c, Transferable data, int action) {
	    if ((action == MOVE) && (indices != null)) {
	      DefaultListModel model = (DefaultListModel) source.getModel();

	      //If we are moving items around in the same list, we
	      //need to adjust the indices accordingly since those
	      //after the insertion point have moved.
	      if(source!=null && source.equals(target))
	    	  if (addCount > 0) {
	    		  for (int i = 0; i < indices.length; i++) {
	    			  if (indices[i] > addIndex) {
	    				  indices[i] += addCount;
	    			  }
	    		  }
	    	  }
	      for (int i = indices.length - 1; i >= 0; i--)
	        model.remove(indices[i]);
	    }
	    indices = null;
	    target=null;
	    source=null;
	    addIndex = -1;
	    addCount = 0;
	  }

	  private boolean hasLocalArrayListFlavor(DataFlavor[] flavors) {
	    if (localArrayListFlavor == null) {
	      return false;
	    }

	    for (int i = 0; i < flavors.length; i++) {
	      if (flavors[i].equals(localArrayListFlavor)) {
	        return true;
	      }
	    }
	    return false;
	  }

	  private boolean hasSerialArrayListFlavor(DataFlavor[] flavors) {
	    if (serialArrayListFlavor == null) {
	      return false;
	    }

	    for (int i = 0; i < flavors.length; i++) {
	      if (flavors[i].equals(serialArrayListFlavor)) {
	        return true;
	      }
	    }
	    return false;
	  }

	  public boolean canImport(JComponent c, DataFlavor[] flavors) {
	    if (hasLocalArrayListFlavor(flavors)) {
	      return true;
	    }
	    if (hasSerialArrayListFlavor(flavors)) {
	      return true;
	    }
	    return false;
	  }

	  protected Transferable createTransferable(JComponent c) {
	    if (c instanceof JList) {
	      source = (JList) c;
	      indices = source.getSelectedIndices();
	      Object[] values = source.getSelectedValues();
	      if (values == null || values.length == 0) {
	        return null;
	      }
	      ArrayList alist = new ArrayList(values.length);
	      for (int i = 0; i < values.length; i++) {
	        Object o = values[i];
	        String str = o.toString();
	        if (str == null)
	          str = "";
	        alist.add(str);
	      }
	      return new ArrayListTransferable(alist);
	    }
	    return null;
	  }

	  public int getSourceActions(JComponent c) {
	    return dragMode;
	  }

	  public class ArrayListTransferable implements Transferable {
	    ArrayList data;

	    public ArrayListTransferable(ArrayList alist) {
	      data = alist;
	    }

	    public Object getTransferData(DataFlavor flavor)
	        throws UnsupportedFlavorException {
	      if (!isDataFlavorSupported(flavor)) {
	        throw new UnsupportedFlavorException(flavor);
	      }
	      return data;
	    }

	    public DataFlavor[] getTransferDataFlavors() {
	      return new DataFlavor[] { localArrayListFlavor,
	          serialArrayListFlavor };
	    }

	    public boolean isDataFlavorSupported(DataFlavor flavor) {
	      if (localArrayListFlavor.equals(flavor)) {
	        return true;
	      }
	      if (serialArrayListFlavor.equals(flavor)) {
	        return true;
	      }
	      return false;
	    }
	  }
	}