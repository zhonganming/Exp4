import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;

public class TestEchoServer {
	public static void main(String[] args) {
		
			NioEventLoopGroup bossGroup = new NioEventLoopGroup();
			NioEventLoopGroup workerGroup = new NioEventLoopGroup();
			try {	
			ServerBootstrap bootstrap = new ServerBootstrap().group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel ch) throws Exception {
					ch.pipeline().addLast(new EchoServerHandler());
				}			
			})
			.option(ChannelOption.SO_BACKLOG, 18)
			.childOption(ChannelOption.SO_KEEPALIVE, true);
			
			ChannelFuture future = bootstrap.bind(8888).sync();
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}		
	}
}


class EchoServerHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		ByteBuf in = (ByteBuf) msg;
//		//System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII));
//		while (in.isReadable()) { // (1)
//            System.out.print((char) in.readByte());
//            System.out.flush();
//        }
		ctx.write(msg + "\r\n");				
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
}