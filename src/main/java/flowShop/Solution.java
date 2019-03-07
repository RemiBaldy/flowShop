package flowShop;

public interface Solution {
    Instance instance();
    int startingTime(int machine, int job);
    int deadline();

}
