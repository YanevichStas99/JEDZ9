package com.company;

public class MyTreeMap implements MyMap {
    private class Node {
        private int key;
        private int value;
        private Node left;
        private Node right;
        private Node perent = null;

        private Node(Object key, Object value) {
            this.key = (Integer) key;
            this.value = (Integer) value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    key +
                    "=" + value +
                    '}';
        }
    }

    private int size;
    private Node root;

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public boolean containsKey(int key) {
        Node tmp = root;
        while (tmp != null) {
            if (key == tmp.key) {
                return true;
            } else {
                if (key < tmp.key) {
                    tmp = tmp.left;
                } else {
                    tmp = tmp.right;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(int value) {
        Node tmp = root;
        while (tmp.left != null) {
            tmp = tmp.left;
        }
        Node previous = tmp.perent;
        while (tmp != root) {
            if (tmp.value == value) {
                return true;
            }
            if (tmp.right != null && tmp.right != previous) {
                previous = tmp;
                tmp = tmp.right;
                while (tmp.left != null) {
                    tmp = tmp.left;
                }
            } else {
                previous = tmp;
                tmp = tmp.perent;
            }
        }
        if (root.value == value) {
            return true;
        }
        tmp = root;
        while (tmp.right != null) {
            tmp = tmp.right;
        }
        previous = tmp.perent;
        while (tmp != root) {
            if (tmp.value == value) {
                return true;
            }
            if (tmp.left != null && tmp.left != previous) {
                previous = tmp;
                tmp = tmp.left;
                while (tmp.right != null) {
                    tmp = tmp.right;
                }
            } else {
                previous = tmp;
                tmp = tmp.perent;
            }
        }
        return false;
    }

    @Override
    public Object get(int key) {
        if (containsKey(key)) {
            Node tmp = root;
            while (tmp != null) {
                if (key == tmp.key) {
                    break;
                } else {
                    if (key < tmp.key) {
                        tmp = tmp.left;
                    } else {
                        tmp = tmp.right;
                    }
                }
            }
            return tmp.value;
        } else {
            return null;
        }

    }

    @Override
    public Object put(Object key, Object value) {
        if (containsKey((int) key)) {
            return null;
        }
        Node node = new Node(key, value);
        if (root == null) {
            root = node;
            size++;
        } else {
            Node tmp = root;
            Node perant = root.perent;
            while (tmp != null) {
                if (node.key < tmp.key) {
                    perant = tmp;
                    tmp = tmp.left;
                } else {
                    perant = tmp;
                    tmp = tmp.right;
                }
            }
            tmp = node;
            tmp.perent = perant;
            if (node.key < perant.key) {
                perant.left = tmp;
            } else {
                perant.right = tmp;
            }
            size++;
        }
        return node;
    }

    @Override
    public Object remove(Object key) {
        if (containsKey((int) key)) {
            Node tmp = root;
            while (tmp != null) {
                if ((int) key == tmp.key) {
                    break;
                } else {
                    if ((int) key < tmp.key) {
                        tmp = tmp.left;
                    } else {
                        tmp = tmp.right;
                    }
                }
            }
            if (tmp == root) {
                Node right = tmp.right;
                Node left = tmp.left;
                while (right.left != null) {
                    right = right.left;
                }
                root = tmp.right;
                right.left = left;
                size--;
                return 1;
            }
            if (tmp.left == null && tmp.right == null) {
                Node perent = tmp.perent;
                if (tmp.key < perent.key) {
                    perent.left = null;
                } else {
                    perent.right = null;
                }
                size--;
                return 1;
            } else {
                if (tmp.right == null) {
                    Node perent = tmp.perent;
                    Node tmpLeft = tmp.left;
                    if (tmp.key < perent.key) {
                        perent.left = tmpLeft;
                    } else {
                        perent.right = tmpLeft;
                    }
                    size--;
                    return 1;
                } else {
                    Node tmpLeft = tmp.left;
                    Node tmpRight = tmp.right;
                    Node perent = tmp.perent;
                    if (tmp.key < perent.key) {
                        perent.left = tmpRight;
                    } else {
                        perent.right = tmpRight;
                    }
                    Node right = tmpRight;
                    while (right.left != null) {
                        right = right.left;
                    }
                    right.left = tmpLeft;
                    size--;
                    return 1;
                }
            }
        } else {
            return null;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        int[] arrKeys = new int[size];
        if (arrKeys.length != 0) {
            int count = 0;
            Node tmp = root;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
            Node previous = tmp.perent;
            while (tmp != root) {
                if (!arrContains(tmp.key, arrKeys)) {
                    arrKeys[count] = tmp.key;
                    count++;
                }
                if (tmp.right != null && tmp.right != previous) {
                    previous = tmp;
                    tmp = tmp.right;
                    while (tmp.left != null) {
                        tmp = tmp.left;
                    }
                } else {
                    previous = tmp;
                    tmp = tmp.perent;
                }
            }
            if (!arrContains(root.key, arrKeys)) {
                arrKeys[count] = root.key;
            }
            tmp = root;
            while (tmp.right != null) {
                tmp = tmp.right;
            }
            previous = tmp.perent;
            count = size - 1;
            while (tmp != root) {
                if (!arrContains(tmp.key, arrKeys)) {
                    arrKeys[count] = tmp.key;
                    count--;
                }
                if (tmp.left != null && tmp.left != previous) {
                    previous = tmp;
                    tmp = tmp.left;
                    while (tmp.right != null) {
                        tmp = tmp.right;
                    }
                } else {
                    previous = tmp;
                    tmp = tmp.perent;
                }
            }
            StringBuilder result = new StringBuilder("MyTreeMap:");
            for (int i = 0; i < arrKeys.length; i++) {
                result.append("{" + arrKeys[i] + "=" + get(arrKeys[i]) + "}");
            }


            return String.valueOf(result);
        }else {
            return "{}";
        }
    }

    private boolean arrContains(int key, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return true;
            }
        }
        return false;
    }
}


