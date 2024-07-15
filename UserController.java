public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<List<User>> findUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    public ResponseEntity<User> createdUser(@RequestBody User user){
        User userPost = userService.createUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userPost.getId())
                .toUri();
        return ResponseEntity.created(location).body(userPost);
    }

   

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        User userUpdate = userService.updateUser(id, user);

        return ResponseEntity.ok(userUpdate);
    }

}
