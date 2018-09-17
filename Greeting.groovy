@RestController
class Greeter {
  
  @GetMapping('/greet')
  def sayHello() {
    return 'hello'
  }
}
