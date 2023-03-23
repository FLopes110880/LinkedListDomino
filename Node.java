//
//
//class Node {
//
//    protected Node left;
//    protected Node right;
//    protected Peca peca;
//    protected Node up;
//    protected Node down;
//    protected Node []direcoes;
//
//    public Node (Peca peca) {
//        this.left = null;
//        this.right = null;
//
//        this.peca = peca;
//
//        if (peca.getClass().getName().equals("Dupla")) {
//            this.up = null;
//            this.down = null;
//            this.direcoes = new Node[]{left, up, right, down};
//        }
//        else {
//            if (peca.getVert()) {
//                this.direcoes = new Node[]{up, down};
//            }
//            else {
//                this.direcoes = new Node[]{left, right};
//            }
//        }
//    }
//}