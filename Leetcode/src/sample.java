public class sample {

    public void sort(int[] arr) {
        int left = 0;
        int right = arr.length-1;
        while(left < right) {
            if (arr[left] == 0) {
                left++;
            } else {
                swap(arr, left, right);
                right--;
            }
        }

        for(int i : arr) {
            System.out.print(i + " ");
        }
    }

    private void swap (int[] arr, int left, int right ) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static void main(String[] args){
        sample s = new sample();
        int[] arr = {0,1,0,0,1,1,1};
        int[] arr2 = { 1, 0, 1,0, 1,0};
        s.sort(arr2);
    }
}


// array [ 0, 1, 0, 0, 1, 1, 1]

// array [ 0,0,0,1,1,1,1,]
// two point left, right
//  [ 0, 1, 0, 0, 1, 1, 1]
//   left              right
//  skip if is '0'; swap if it is '1'
//  [ 0, 1, 0,0, 1, 1, 1]
//      left       right
//   [0, 1, 0, 0, 1, 1, 1]
//      left  right
//  [ 0, 0, 0, 1 , 1, 1, 1,]
//      left;right

// solid
// interface

// interface
// two class implement this interface
// new

//  List<String> fruit = new ArrayList<>();
//   add(); remove()
//  fruit.add()
//  delete() ; remove(); add();
//  fruit.equal()
//   linkedList
//

// reservation system
// reservation entity( id; name; time;
// reservation table: (  reservation id; customerID; time ; numble)
// customer table: ( customerID,
// table for four ; table for two
// 4  price 1000 ; 4 price 500
//

// hashmap  currentHash

// microserver : monilith
//  